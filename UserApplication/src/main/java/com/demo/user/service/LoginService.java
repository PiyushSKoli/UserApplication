package com.demo.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.user.dto.LoginDto;
import com.demo.user.entity.Users;
import com.demo.user.repository.UserRepository;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;

	public LoginDto login(String emailId, String password) {
		HttpSession session;
		HttpServletRequest req= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		LoginDto loginDto = new LoginDto();
		Users user=userRepository.findByEmailAndPassword(emailId, password);
		if(user!=null) {
			session= req.getSession(true);
			session.setAttribute(user.getRole(), user.getRole());
			session.setMaxInactiveInterval(7*60);
			loginDto.setDesignation(user.getDesignation());
			loginDto.setName(user.getName());
			loginDto.setSessionId(session.getId());
			loginDto.setUserRole(user.getRole());
		}else {
			return null;
		}
		return loginDto;
	}
	
    public void destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}