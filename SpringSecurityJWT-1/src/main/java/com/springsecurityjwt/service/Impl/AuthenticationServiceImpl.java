package com.springsecurityjwt.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurityjwt.dto.SignUpRequest;
import com.springsecurityjwt.entity.Role;
import com.springsecurityjwt.entity.User;
import com.springsecurityjwt.repositories.UserRepository;
import com.springsecurityjwt.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository usrRepo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	public User signup(SignUpRequest signUpRequest) {
		
		User user = new User();
		user.setFirstname(signUpRequest.getFirstName());
		user.setSecondname(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passEncoder.encode(signUpRequest.getPassword()));
		user.setRole(Role.USER);
		return usrRepo.save(user);
	}
}
