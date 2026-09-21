package com.peakwonders09.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record TripRequest(
	@NotBlank
	String title,
	
	@NotBlank
	String destination,
	
	@Size(max=2000)
	String description,
	
	@NotNull
	@Min(1)
	Integer durationDays,
	
	@NotNull
	@DecimalMin("0.0")
	BigDecimal price,
	
	String imgUrl,
	String category
	){}
