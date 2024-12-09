package com.springboot.fullstack.simple_crud.Exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(Long id) {
		super("Sorry, The User with ID "+id+" doesn't exist.");
	}
}
