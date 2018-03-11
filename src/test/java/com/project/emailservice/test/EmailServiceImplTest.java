package com.project.emailservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Before;
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
	
	File file = null;
	
	@Autowired
	EmailService emailService;
	
	@Before
	public void setUp() {
		//Please uncomment this line and provide the path of the file you want to upload when you run the test case
		String path = "/Users/vinayanayak/Documents/EmailList1.csv";
		 file = new File(path);
		assertThat(file.exists(),is(true));
	}
	
	@Test
	public void testparseCsvFile() {
		String path = "/Users/vinayanayak/Documents/EmailList1.csv";
		Path filePath = new Path();
		filePath.setFilePath(path);
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
