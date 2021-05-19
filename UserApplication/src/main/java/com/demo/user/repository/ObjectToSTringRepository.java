package com.demo.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.user.entity.ObjectToString;

@Repository
public interface ObjectToSTringRepository extends CrudRepository<ObjectToString, Integer> {

}
