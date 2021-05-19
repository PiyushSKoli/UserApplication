package com.demo.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.user.entity.Users;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UserDataFetures implements DataFetcher<Users>{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public UserDataFetures(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	public Users get(DataFetchingEnvironment environment) {
		String userId=environment.getArgument("userId");
		return userRepository.findByUserId(userId);
	}
	
	
	
}
