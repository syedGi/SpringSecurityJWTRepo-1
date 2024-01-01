package com.springsecurityjwt.service.Impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springsecurityjwt.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {

	final String SECRET = "413F442847284862506553685660597033733676397924422645294840406351";

//	private String generateToken(UserDetails userDeatils) {
	public String generateToken(UserDetails userDeatils) {
		return Jwts.builder().setSubject(userDeatils.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
//				.signWith(SignatureAlgorithm.HS256, getSiginKey())
				.compact();
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimResolvers.apply(claims);
	}

	private Key getSiginKey() {
		byte[] key = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(key);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	@Override
	public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 604800000))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
//				.signWith(SignatureAlgorithm.HS256, getSiginKey())
				.compact();
	}
}
