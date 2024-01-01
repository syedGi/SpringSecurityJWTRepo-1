package com.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityjwt.dto.JWTAuthenticationResponse;
import com.springsecurityjwt.dto.RefreshTokenRequest;
import com.springsecurityjwt.dto.SignInRequest;
import com.springsecurityjwt.dto.SignUpRequest;
import com.springsecurityjwt.entity.User;
import com.springsecurityjwt.service.AuthenticationService;
import com.springsecurityjwt.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authServ;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignUpRequest signupReq){
		return ResponseEntity.ok(authServ.signup(signupReq));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JWTAuthenticationResponse> signin(@RequestBody SignInRequest signinReq){
		return ResponseEntity.ok(authServ.signin(signinReq));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(authServ.refreshToken(refreshTokenRequest));
	}
}
