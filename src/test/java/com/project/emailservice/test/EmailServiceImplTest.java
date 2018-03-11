package com.project.emailservice.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.emailservice.exception.SendFailedException;
import com.project.emailservice.model.Path;
import com.project.emailservice.model.Person;
import com.project.emailservice.service.EmailService;
import com.project.emailservice.service.impl.EmailServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {
	
	@Autowired
	EmailService emailService;
	
	@Test
	public void testparseCsvFile() {
		URL url = this.getClass().getResource("/EmailList.csv");
		Path filePath = new Path();
		filePath.setFilePath(url.getPath());
		boolean result = emailService.processFile(filePath);
		assertEquals(true, result);
	}
	
	@Test
	public void testSendEmail() throws InterruptedException, SendFailedException {
		Person person = createPerson();
		EmailServiceImpl service = new EmailServiceImpl();
		boolean result = service.sendEmail(person);
		assertEquals(true, result);
	}
	
	private Person createPerson() {
		Person person = new Person("vinaya.nayak@abc.com", "vinaya", "nayak");
		return person;
	}

}
