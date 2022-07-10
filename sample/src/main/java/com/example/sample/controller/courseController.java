package com.example.sample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.bean.Course;
import com.example.sample.repository.CourseRepository;

@RestController
@RequestMapping("/course")
public class courseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@RequestMapping("/findall")
	public List<Course> findAll(){
		return courseRepository.findAll();
	}
	
	@RequestMapping("/findall/{id}")
	public Course findbyId(@PathVariable int id) {
		Optional<Course> cc=courseRepository.findById(id);
		if(cc.isEmpty()) {
			throw new RuntimeException("Course not found "+id);
		}
		return cc.get();
	}
	
	@PostMapping("/add")
	public String add(@RequestBody Course course) {
		courseRepository.save(course);
		return "course added "+course.getId();
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		courseRepository.deleteById(id);
		return "course with id "+id+" deleted.";
	}
	

}
