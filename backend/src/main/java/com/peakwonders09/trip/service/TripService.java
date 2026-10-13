package com.peakwonders09.trip.service;

import com.peakwonders09.trip.dto.*;
import org.springframework.data.domain.*;

public interface TripService{
	TripResponse create(TripRequest req);
	TripResponse getById(Long id);
	
	Page<TripResponse> getAll(Pageable pageable);
	Page<TripResponse> search( String destination, Pageable pageable);
	
	TripResponse update(Long id, TripRequest req);
	
	void delete(Long id);	
}
