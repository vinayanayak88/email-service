package com.project.emailservice.service;

import org.springframework.stereotype.Service;

import com.project.emailservice.model.Path;

@Service
public interface EmailService {
	
	public boolean processFile(Path path);

}
