package com.springsecurityjwt.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

	public String extractUserName(String token);
	String generateToken(UserDetails userDeatils);
	boolean isTokenValid(String token, UserDetails userDetails);
}
