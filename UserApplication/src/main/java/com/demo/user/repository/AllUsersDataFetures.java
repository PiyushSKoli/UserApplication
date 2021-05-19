package com.demo.user.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.user.entity.Users;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllUsersDataFetures implements DataFetcher<List<Users>> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public AllUsersDataFetures(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public List<Users> get(DataFetchingEnvironment environment) {
		return (List<Users>) userRepository.findAll();
	}
	
}
