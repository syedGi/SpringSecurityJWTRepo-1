package com.springsecurityjwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JWTService {

	public String extractUserName(String token);
	String generateToken(UserDetails userDeatils);
	boolean isTokenValid(String token, UserDetails userDetails);
}
