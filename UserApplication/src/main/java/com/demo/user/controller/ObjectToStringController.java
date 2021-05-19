package com.demo.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.dto.ResultModel;
import com.demo.user.entity.ObjectToString;
import com.demo.user.entity.Users;
import com.demo.user.service.ObjectToStringService;

@RestController
@RequestMapping(value="objectToString")
public class ObjectToStringController {
	
	@Autowired
	private ObjectToStringService objectToStringService;
	
	Logger logger=LoggerFactory.getLogger(ObjectToStringController.class);

	@PostMapping("/saveData")
	public ResponseEntity<ResultModel> cretaeData(@RequestBody List<ObjectToString> objectToStrings) {
		ResultModel resultModel = new ResultModel();
		logger.info("Creating/Updating data in Users.......");
		try {
			ObjectToString response = objectToStringService.saveJson(objectToStrings);
			if (response!=null) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				logger.info("Creating/Updating User Successfully.......");
			} else {
				resultModel.setMessage("Failed");
				//resultModel.setMessageList(response);
				logger.info("" + response);
			}
		} catch (Exception e) {
			logger.info("Error Occure" + e);
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<ResultModel>(resultModel, HttpStatus.CREATED);
	}
}
