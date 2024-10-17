package com.example.MailSernder.Entity;

import lombok.Data;
import lombok.Setter;

@Data
public class EmailDetails {
	 	private String recipient;
	    private String msgBody;
	    private String subject;
	    private String signature;
	
		
	    

}
