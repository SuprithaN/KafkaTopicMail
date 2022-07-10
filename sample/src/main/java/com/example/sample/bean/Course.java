package com.example.sample.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
	
	@Id
	@GeneratedValue
	private int Id;
	
	private String Name;
	
	private String Author;
	
	public Course() {}

	public Course(int id, String name, String author) {
		super();
		Id = id;
		Name = name;
		Author = author;
	}

	@Override
	public String toString() {
		return "Course [Id=" + Id + ", Name=" + Name + ", Author=" + Author + "]";
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}
}

