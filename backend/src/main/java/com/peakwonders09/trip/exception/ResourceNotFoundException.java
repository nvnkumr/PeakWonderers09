package com.peakwonders09.trip.exception;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message){
		super(message);
	}
}
