package com.springsecurityjwt.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springsecurityjwt.entity.User;

@Service
public interface JWTService {

	public String extractUserName(String token);
	String generateToken(UserDetails userDeatils);
	boolean isTokenValid(String token, UserDetails userDetails);
//	public String generateRefreshToken(HashMap hashMap, User user);
//	String generateRefreshToken(HashMap hashMap, UserDetails user);
	String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
