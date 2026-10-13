package com.peakwonders09.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService{
	private final Key key;
	
	public JwtService(@Value("$JWT_SECRET}") String secret){
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(String email){
		return Jwts.builder()
			.subject(email)
			.issuedAt(new Date())
			.expiration(
				new Date( 
					System.currentTimeMillis() + 1000L * 60 * 60 * 24))
			.signWith(key)
			.compact();
		}
	
	public String extractEmail(String token){
		return Jwts.parser()
			.verifyWith((javax.crypto.SecretKey) key)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getSubject();
		}
		
	public boolean isValid(String token){
		try{
			extractEmail(token);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
