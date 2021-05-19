package com.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.service.UsersGraphService;

import graphql.ExecutionResult;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value="userGraph/user")
public class UsersGraphController {

	@Autowired
	private UsersGraphService usersGraphService;
	
	@Autowired
    public UsersGraphController(UsersGraphService usersGraphService) {
        this.usersGraphService=usersGraphService;
    }
	
	@PostMapping
	public ResponseEntity<Object> allAllUsers(@RequestBody String query) {
		ExecutionResult result=usersGraphService.graphQL().execute(query);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String workingTest() {
		return "working";
	}
}
