package com.springsecurityjwt.service;

import org.springframework.stereotype.Service;

import com.springsecurityjwt.dto.SignUpRequest;
import com.springsecurityjwt.entity.User;

@Service
public interface AuthenticationService {

	User signup(SignUpRequest signUpRequest);
}
