package com.example.sample.service;

import org.springframework.stereotype.Service;

import com.example.sample.bean.Course;
import com.example.sample.bean.CourseDetailsMail;

@Service
public class kafkaService {
	
	public CourseDetailsMail sendmail(Course course) {
	
		CourseDetailsMail courseDetailsMail= new CourseDetailsMail();
		courseDetailsMail.setId(course.getId());
		courseDetailsMail.setAuthor(course.getAuthor());
		courseDetailsMail.setName(course.getName());
		return courseDetailsMail;
		
	}

}
