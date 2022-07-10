package com.example.sample;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.example.sample.bean.CourseDetailsMail;

@Component
public class KafkaListeners {
	
    @Autowired
    private JavaMailSender javaMailSender;

	@KafkaListener(topics = "coursedetails", groupId = "courses")
	void listener(@Payload CourseDetailsMail courseDetailsMail) throws MessagingException {
		sendMail(courseDetailsMail);
	}

	private void sendMail(CourseDetailsMail courseDetailsMail) throws MessagingException {
		String from = "suprithan848@gmail.com";
		String to = "supi.nagaraj@gmail.com";

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setSubject("Kafka Topic Mail Test");
		helper.setFrom(from);
		helper.setTo(to);
		
		
		   String text = "<h1>Courses</h1>\n" +
	                "<p>Please find the below Courses details</p>" +
	                "<p><b>Course Id </b>" + courseDetailsMail.getId()+ "</p>" +
	                "<p><b>Name: </b>" + courseDetailsMail.getName() + "</p>\n\n" +
	                "<p><b>Author: </b>" + courseDetailsMail.getAuthor() + "</p>\n\n";
	        helper.setText(text, true);

	        javaMailSender.send(message);
	}

}
