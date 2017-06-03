package com.richard.brewer.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.richard.brewer.model.Sale;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void send(Sale sale) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ricardolvsoftware@gmail.com");
		message.setTo(sale.getClient().getEmail());
		message.setSubject("Opa funcionou");
		message.setText("Obrigado!");
		
		mailSender.send(message);
	}

}
