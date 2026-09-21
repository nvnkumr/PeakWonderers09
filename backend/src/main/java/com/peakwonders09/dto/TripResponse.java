package com.peakwonders09.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TripResponse(
	Long id,
	String title,
	String destination,
	String description,
	Integer durationDays,
	BigDecimal price,
	String imgUrl,
	String category,
	Boolean active,
	LocalDateTime createdAt
){}
