package com.springboot.fullstack.simple_crud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fullstack.simple_crud.Repository.UserRepository;
import com.springboot.fullstack.simple_crud.model.User;
import com.springboot.fullstack.simple_crud.Exception.UserNotFoundException;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 ADDING NEW USER
	 */
	@PostMapping("/user")  
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	/*
	 GETTING ALL USERS
	 */
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	/*
	 FINDING(getting) a USER WITH ID
	 */
	@GetMapping("/users/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}
	
	 /*
	 Editing/UPDATING THE EXISTING USER
	 */
	@PutMapping("/users/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable Long id) {
		return userRepository.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					return userRepository.save(user);
				}).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	 /*
	 DELETING THE EXISTING USER
	 */
	 @DeleteMapping("/users/{id}")
	 String deleteUser(@PathVariable Long id) {
		 
		 //If ID doesn't exist
		 if(!userRepository.existsById(id)) {
			 throw new UserNotFoundException(id);
		 }
		 
		 //Delete
		 userRepository.deleteById(id);
		 return "User with Id "+id+" has been successfully deleted." ;
	 }
}
