package com.example.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.bean.Author;
import com.example.sample.bean.Course;
import com.example.sample.bean.CourseDetailsMail;
import com.example.sample.bean.MessageRequest;
import com.example.sample.repository.AuthorRepository;
import com.example.sample.service.MessagingService;
import com.example.sample.service.kafkaService;

@RestController

public class Authorcontroller {
 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private kafkaService kafkaService;
	
	private KafkaTemplate<String, CourseDetailsMail> kafkaTemplate;
	
	@Autowired
	private MessagingService<CourseDetailsMail> mailMessagingService;
	
	@PostMapping("/addauth")
	public String addAuthor(@RequestBody Author author) {
		
		String pwd=author.getPassword();
		String encryptedpwd=passwordEncoder.encode(pwd);
		author.setPassword(encryptedpwd);
		authorRepository.save(author);
		return "Author sucessfully added";
		
	}
	
	
	
	public Authorcontroller(KafkaTemplate<String, CourseDetailsMail> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}



	@PostMapping("/kafkamessage")
	public ResponseEntity<Map<String, String>> publish(@RequestBody Course course) {
		CourseDetailsMail courseDetailsMail=kafkaService.sendmail(course);
		this.mailMessagingService.sendMessageToTopic("coursedetails", courseDetailsMail);
        Map<String, String> map = new HashMap<>();
        map.put("acknowledgement", String.format("Changes Worked"));
        map.put("message", String.format("kafka course project sucessfull"));
        return ResponseEntity.ok().body(map);
		
	//	kafkaTemplate.send("coursedetails", courseDetailsMail.toString());
		
	}
}
