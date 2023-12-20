package com.culture.demo.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService{

	@Autowired
	private JavaMailSender mailSender;
	
	public String sendEmail(String toAddress, String fromAddress,
			String subject, String msgBody, boolean isHtml) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
	    	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	    	messageHelper.setSubject(subject);
	    	messageHelper.setTo(toAddress);
	    	messageHelper.setFrom(fromAddress, "문화센터");
	    	messageHelper.setText(msgBody,isHtml);
	    	mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		
		return "Success";
	}

}
