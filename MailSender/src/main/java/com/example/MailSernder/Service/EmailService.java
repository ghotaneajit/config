package com.example.MailSernder.Service;

import com.example.MailSernder.Entity.EmailDetails;

public interface EmailService {
	
	
	 // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
   String sendMailWithAttachment(EmailDetails details);

}
