package com.peakwonders09.auth.service;

import com.peakwonders09.auth.dto.*;
import com.peakwonders09.auth.entity.User;
import com.peakwonders09.auth.repository.UserRepository;
import com.peakwonders09.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthResponse register(RegisterRequest req){
		if(userRepository.existByEmail(req.email())){
			throw new IllegalArgumentException("Email already Registered");
		}
		
		User user = User.builder()
			.fullName(req.fullName())
			.email(req.email().toLowerCase())
			.password(passwordEncoder.encode(req.password()))
			.role(User.Role.CUSTOMER)
			.build();
			
		userRepository.save(user);
		return createResponse(user);
	}
	
	public AuthResponse login(LoginRequest req){
		User user = userRepository
			.findByEmail(req.email().toLowerCase())
			.orElseThrow(()-> new IllegalArgumentException("Invalid Email or Password"));
			
		if (!passwordEncoder.matches(req.password(),user.getPassword())){
			throw new IllegalArgumentException("Invalid Email or Password");
		}
		
		return createResponse(user);
	}
	
	private AuthResponse createResponse(User user){
		return new AuthResponse(
			jwtService.generateToken(user.getEmail()),
			user.getId(),
			user.getFullName(),
			user.getEmail(),
			user.getRole().name()
		);
	}
}
