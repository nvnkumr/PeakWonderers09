package com.peakwonders09;//pkg-> where the class belongs

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;//allows sboot to start

@SpringBootApplication //tells sb this is main application class
public class PeakWonders09Application{
	public static void main(String[] args){
		SpringApplication.run(PeakWonders09Application.class, args); //start sb application
	}
}
