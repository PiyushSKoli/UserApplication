package com.demo.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.dto.LoginDto;
import com.demo.user.dto.ResultModel;
import com.demo.user.service.LoginService;

@RestController
@RequestMapping(value="login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	Logger logger=LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value="/empLogin/{emailId}/{password}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultModel> login(@PathVariable("emailId") String emailId , @PathVariable("password") String password){
		ResultModel resultModel = new ResultModel();
		logger.info("Authenticating...........");
		try{
			LoginDto response=loginService.login(emailId, password);
			if(response!=null) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				logger.info("Authentication Successfully.......");
			}else {
				resultModel.setMessage("Failed");
				logger.info("Authentication Failed.......");
			}		
		}catch(Exception e){
			resultModel.setMessage("Error");
			logger.info("Error Occure"+e);
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	return new ResponseEntity<ResultModel>(resultModel,HttpStatus.OK);
	}
	
/*	@RequestMapping(value="/empLogout/{sessionId}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultModel> deskLogout(@PathVariable("sessionId") String sessionId){
		ResultModel resultModel = new ResultModel();
		logger.info("Log Out....!");
		try{
			loginService.logout(sessionId);
			resultModel.setMessage("Success");
			logger.info("Log Out Successfully.....!");
		}catch(Exception e){
			resultModel.setMessage("Error");
			logger.info("Log Out Failed......!");
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
	}*/
	
	@PostMapping("/invalidate/session")
    public ResponseEntity<ResultModel> destroySession(HttpServletRequest request) {
		ResultModel resultModel = new ResultModel();
		logger.info("Log Out....!");
		try{
			loginService.destroySession(request);
			resultModel.setMessage("Success");
			logger.info("Log Out Successfully.....!");
		}catch(Exception e){
			resultModel.setMessage("Error");
			logger.info("Log Out Failed......!");
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
    }
}
