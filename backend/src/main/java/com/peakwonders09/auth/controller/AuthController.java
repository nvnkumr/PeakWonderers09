package com.peakwonders09.auth.controller;

import com.peakwonders09.auth.dto.*;
import com.peakwonders09.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController{
	private final AuthService authService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public AuthResponse register(@Valid @RequestBody RegisterRequest req){
		return authService.register(req);
	}
	
	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody LoginRequest req){
		return authService.login(req);
	}
}
