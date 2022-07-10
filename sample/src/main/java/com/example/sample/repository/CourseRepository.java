package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.bean.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
