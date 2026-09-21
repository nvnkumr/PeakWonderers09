package com.peakwonders09.controller;

import com.peakwonders09.dto.*;
import com.peakwonders09.service.TripService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trips") //api endpoints
@RequiredArgsConstructor
public class TripController{
	private final TripService tripService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TripResponse create(@Valid @RequestBody TripRequest req){
		return tripService.create(req);
	}
	
	@GetMapping("/{id}")
	public TripResponse getById(@PathVariable Long id){
		return tripService.getById(id);
	}
	
	@GetMapping
	public Page<TripResponse> getAll(Pageable pageable){
		return tripService.getAll(pageable);
	}
	
	@GetMapping("/search")
	public Page<TripResponse> search(@RequestParam String destination,Pageable pageable){
		return tripService.search(destination,pageable);
	}
	
	@PutMapping("/{id}")
	public TripResponse update(@PathVariable Long id, @Valid @RequestBody TripRequest req){
		return tripService.update(id, req);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		tripService.delete(id);
	}	
}

/* architecture flow
 * http req -> tripcontroller -> tripservice -> triprepo ->postgresql*/
