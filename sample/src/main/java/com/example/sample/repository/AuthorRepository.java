package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.bean.Author;



public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	

	Author findByUserName(String username);
	

}
