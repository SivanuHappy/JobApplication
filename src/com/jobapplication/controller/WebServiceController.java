package com.jobapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobapplication.entity.User;
import com.jobapplication.service.UserService;

@RestController
@RequestMapping("/ws")
public class WebServiceController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/listUsers")
	public List<User> getCustomers() {
		List<User> theUsers = userService.getUsers();
		return theUsers;
	}
	
	@GetMapping("/listUsers/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id){
		User theUser = userService.getUserById(id);
		if(theUser == null){
			System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(theUser, HttpStatus.OK);
	}
}
