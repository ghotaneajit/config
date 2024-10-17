package com.example.MailSernder.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MailSernder.Entity.EmailDetails;
import com.example.MailSernder.Service.EmailService;
import com.example.MailSernder.ServiceImpl.EmailServiceImpl;

@RestController
public class SendMail {
	
	
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendMail")
	public String send(@RequestBody EmailDetails emailDetails  ) {
	return emailService.sendSimpleMail(emailDetails) ;
	}
	
	
	  @PostMapping("/sendMailWithAttachment") public String sendMailWithAttachment(
	  @RequestBody EmailDetails details) { 
		  String status =emailService.sendMailWithAttachment(details);
	  
	  return status; }
	 

}
