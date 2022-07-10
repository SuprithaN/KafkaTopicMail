package com.example.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sample.bean.Author;
import com.example.sample.repository.AuthorRepository;

@Service
public class userAuthenticationService implements UserDetailsService{
	
	@Autowired
	private AuthorRepository authorRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Author author =authorRepository.findByUserName(username);
		userAuthentication userDetails =null;
		if(author!=null) {
			userDetails=new userAuthentication();			//userDetails is existing class of spring-boot imported in userAuthentication
			userDetails.setAuthor(author);
		}
		else {
			throw new UsernameNotFoundException("User does Not Exists"+ username);
		}
		return userDetails;
	}

}
