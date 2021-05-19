package com.demo.user.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.dto.ResultModel;
import com.demo.user.entity.Users;
import com.demo.user.service.UserService;


@RestController
@RequestMapping(value="user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<ResultModel> getAllUsers() {
		ResultModel resultModel = new ResultModel();
		logger.info("Getting All Users From Users");
		try {
			List<Users> response = userService.getAllUsers();
			if (!response.isEmpty()) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				logger.info("Getting All Users Successfully....!");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			} else {
				resultModel.setMessage("No Records Found In Users....!");
				logger.info("No Records Found In Users....!");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("Getting Users Failed.....!");
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PostMapping("/saveUpdateUser")
	public ResponseEntity<ResultModel> cretaeUpdateUser(@RequestBody Users user) {
		ResultModel resultModel = new ResultModel();
		logger.info("Creating/Updating data in Users.......");
		try {
			List<String> response = userService.saveUser(user);
			if (response.contains("Success")) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				logger.info("Creating/Updating User Successfully.......");
			} else {
				resultModel.setMessage("Failed");
				resultModel.setMessageList(response);
				logger.info("" + response);
			}
		} catch (Exception e) {
			logger.info("Error Occure" + e);
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<ResultModel>(resultModel, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<ResultModel> deleteById(@PathVariable("id") Integer id) {
		ResultModel resultModel = new ResultModel();
		logger.info("Deleting  User in Users.......");
		try {
			String response = userService.deleteUser(id);
			if (response.equals("Success")) {
				resultModel.setMessage(response);
				logger.info("Deleting User Successfully.......");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			} else {
				resultModel.setMessage(response);
				logger.info("Id Not Present In Users.......");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			resultModel.setMessage("Failed");
			logger.info("Error Occure" + e);
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<ResultModel> getByUserId(@PathVariable("userId") String userId) {
		ResultModel resultModel = new ResultModel();
		logger.info("Getting User By UserId From Users");
		try {
			Users response = userService.getById(userId);
			if (response!=null) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				logger.info("Getting User By UserId Successfully....!");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			} else {
				resultModel.setMessage("No Records Found In Users....!");
				logger.info("No Records Found In Users for userId :- " + userId + "....!");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("Getting User by UserId Failed.....!");
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}