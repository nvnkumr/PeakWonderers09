package com.peakwonders09.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank String fullName, @NotBlank @Email String email,@NotBlank @Size(min=8,max=100) String password){
	}
