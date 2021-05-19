package com.demo.user.dto;

import java.util.List;

import lombok.Data;
@Data
public class ResultModel {

	private String message;
	
	private Object data;
	
	private List<String> messageList;
}
