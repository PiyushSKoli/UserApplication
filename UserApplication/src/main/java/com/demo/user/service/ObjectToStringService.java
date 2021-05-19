package com.demo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.user.entity.ObjectToString;
import com.demo.user.repository.ObjectToSTringRepository;

@Service
public class ObjectToStringService {

	@Autowired
	private ObjectToSTringRepository objectToSTringRepository;
	
	public ObjectToString saveJson(List<ObjectToString> objectToString ){
		ObjectToString obj=new ObjectToString();
		obj.setObjectToString(objectToString.toString());
		return objectToSTringRepository.save(obj);
	}
}
