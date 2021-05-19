package com.demo.user.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.user.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

	public Users findByUserId(String userId);
	
	public Users findByEmailAndPassword(String email,String password);
}
