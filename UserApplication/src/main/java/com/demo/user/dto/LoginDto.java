package com.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {


	private String sessionId;
	
	private String name;
	
	private String designation;
	
	private String userRole;
}
