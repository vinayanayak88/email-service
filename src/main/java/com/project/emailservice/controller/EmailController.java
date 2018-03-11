package com.project.emailservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.emailservice.model.Path;
import com.project.emailservice.service.EmailService;

@RestController
public class EmailController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	EmailService emailSerice;
	
	@PostMapping("/email")
	public ResponseEntity<String> sendEmail(@RequestBody Path path) {
		logger.info("Received request for processing the file");
		boolean result = emailSerice.processFile(path);
		if(result) {
			return new ResponseEntity<>("Email sent successfully ", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error occurred while sending email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
