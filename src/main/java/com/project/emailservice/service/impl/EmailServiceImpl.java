package com.project.emailservice.service.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.emailservice.exception.SendFailedException;
import com.project.emailservice.model.Path;
import com.project.emailservice.model.Person;
import com.project.emailservice.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public boolean processFile(Path path) {
		boolean result = parseCsvFile(path.getFilePath());
		return result;
	}
	
	public boolean parseCsvFile(String path) {
		try (Stream<String> stream = Files.lines(Paths.get(path), Charset.forName("UTF-8"))) {
			// skip the header
			stream.skip(1).map(line -> {
				return extractPersonData(line);
			}).forEach(p -> {
				try {
					sendEmail(p);
				} catch (InterruptedException e) {
					//store the data and we can retry sending email for the failed recipients and log the message
					logger.warn("Failed sending email to " + p.getEmail() + " ["+ p.getFirstName() +  "  " + p.getLastName() + "]");
					e.printStackTrace();
				} catch (SendFailedException e) {
					//store the data and we can retry sending email for the failed recipients and log the message
					logger.warn("Failed sending email to " + p.getEmail() + " ["+ p.getFirstName() +  "  " + p.getLastName() + "]");
				}
			});
			logger.info("Email sent to the recipients");
		} catch (IOException ioe) {
			logger.warn("IOException occurred while processing the file: " + ioe.getMessage());
		} catch (Exception e) {
			logger.warn("Exception occurred while processing the file : " + e.getMessage());
		}
		return true;
	}

	/**
	 * This method takes every line of the file and splits it into array of emailId, firstname, lastname and returns Person object
	 * @param line
	 * @return
	 */
	private  Person extractPersonData(String line) {
		String[] str = Arrays.stream(line.split(",")).map(s -> s.replace("\"", "")).toArray(String[]::new);
		Person p = new Person(str[0], str[1], str[2]);
		return p;
	}

	/**
	 * This method waits for 5 secs and logs the message
	 * @param person
	 * @throws InterruptedException
	 */
	public boolean sendEmail(Person person) throws InterruptedException, SendFailedException{
		try {
			//email sending logic goes here
			Thread.sleep(500);
			logger.info("Email sent to " + person.getEmail() + " ["+ person.getFirstName() +  "  " + person.getLastName() + "] successfully");
		}
		catch(Exception e) {
			throw new SendFailedException(e.getMessage());
		}
		return true;
	}

}
