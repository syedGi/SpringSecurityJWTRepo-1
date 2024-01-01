package com.springsecurityjwt.service;

import org.springframework.stereotype.Service;

import com.springsecurityjwt.dto.JWTAuthenticationResponse;
import com.springsecurityjwt.dto.RefreshTokenRequest;
import com.springsecurityjwt.dto.SignInRequest;
import com.springsecurityjwt.dto.SignUpRequest;
import com.springsecurityjwt.entity.User;

@Service
public interface AuthenticationService {

	User signup(SignUpRequest signUpRequest);
	JWTAuthenticationResponse signin(SignInRequest signInRequest);
	JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
