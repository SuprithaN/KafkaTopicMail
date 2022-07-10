package com.example.sample.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CourseDetailsMail {

	private int Id;
	
	private String Name;
	
	private String Author;

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

	@Override
	public String toString() {
		return "CourseDetailsMail [Id=" + Id + ", Name=" + Name + ", Author=" + Author + "]";
	}
}
