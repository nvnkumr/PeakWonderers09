package com.peakwonders09.booking.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController{
	//Add BookingService using constructor injection.
	@GetMapping("/test")
	public String test(){
		return "Booking Controller is working!";
	}
}
