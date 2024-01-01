package com.springsecurityjwt.service.Impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurityjwt.dto.JWTAuthenticationResponse;
import com.springsecurityjwt.dto.RefreshTokenRequest;
import com.springsecurityjwt.dto.SignInRequest;
import com.springsecurityjwt.dto.SignUpRequest;
import com.springsecurityjwt.entity.Role;
import com.springsecurityjwt.entity.User;
import com.springsecurityjwt.repositories.UserRepository;
import com.springsecurityjwt.service.AuthenticationService;
import com.springsecurityjwt.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository usrRepo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	private final AuthenticationManager authManager;
	
	private final JWTService jwtService;
	
	public User signup(SignUpRequest signUpRequest) {
		
		User user = new User();
		user.setFirstname(signUpRequest.getFirstName());
		user.setSecondname(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passEncoder.encode(signUpRequest.getPassword()));
		user.setRole(Role.USER);
		return usrRepo.save(user);
	}
	
	public JWTAuthenticationResponse signin(SignInRequest signInRequest) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(
					signInRequest.getEmail(), signInRequest.getPassword()));
		var user = usrRepo.findByEmail(signInRequest.getEmail())
				.orElseThrow( ()-> new IllegalArgumentException("Invalid Email or Password"));
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
		
		JWTAuthenticationResponse jwtAuthResp = new JWTAuthenticationResponse();
		jwtAuthResp.setToken(jwt);
		jwtAuthResp.setRefreshToken(refreshToken);
		return jwtAuthResp;
	}
	
	public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = usrRepo.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt = jwtService.generateToken(user);
			
			JWTAuthenticationResponse jwtAuthResp = new JWTAuthenticationResponse();
			jwtAuthResp.setToken(jwt);
			jwtAuthResp.setRefreshToken(refreshTokenRequest.getToken());
			return jwtAuthResp;
		}
		return null;
	}
}
