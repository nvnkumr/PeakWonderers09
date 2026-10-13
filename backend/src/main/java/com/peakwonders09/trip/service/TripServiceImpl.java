package com.peakwonders09.trip.service;

import com.peakwonders09.trip.dto.*;
import com.peakwonders09.trip.entity.Trip;
import com.peakwonders09.trip.exception.ResourceNotFoundException;
import com.peakwonders09.trip.repository.TripRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TripServiceImpl implements TripService{
	
	private final TripRepository tripRepository;
	
	@Override
	public TripResponse create(TripRequest req){
		Trip trip = Trip.builder()
			.title(req.title())
			.destination(req.destination())
			.description(req.description())
			.durationDays(req.durationDays())
			.price(req.price())
			.imgUrl(req.imgUrl())
			.category(req.category())
			.active(true)
			.build();
			
		return toResponse(tripRepository.save(trip));
	}
	
	@Override
	@Transactional(readOnly=true)
	public TripResponse getById(Long id){
		Trip trip = tripRepository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("Trip not found:"+id));
		return toResponse(trip);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<TripResponse> getAll(Pageable pageable){
		return tripRepository
			.findByActiveTrue(pageable)
			.map(this::toResponse);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<TripResponse> search(String destination, Pageable pageable){
		return tripRepository
			.findByDestinationContainingIgnoreCase(destination, pageable)
			.map(this::toResponse);
	}
	
	@Override
	public TripResponse update(Long id, TripRequest req){
		Trip trip = tripRepository.findById(id)
				.orElseThrow(()->
					new ResourceNotFoundException("Trip not FOund:"+id));
		
		trip.setTitle(req.title());
		trip.setDestination(req.destination());
		trip.setDescription(req.description());
		trip.setDurationDays(req.durationDays());
		trip.setPrice(req.price());
		trip.setImgUrl(req.imgUrl());
		trip.setCategory(req.category());
		
		return toResponse(tripRepository.save(trip));
	}
	
	@Override
	public void delete(Long id){
		Trip trip = tripRepository.findById(id)
			.orElseThrow(()->
					new ResourceNotFoundException("Trip not FOund:"+id));
					
		//Soft delete
		trip.setActive(false);
		tripRepository.save(trip);
	}
	
	private TripResponse toResponse(Trip trip){
		return new TripResponse(trip.getId(),
			trip.getTitle(),
			trip.getDestination(),
			trip.getDescription(),
			trip.getDurationDays(),
			trip.getPrice(),
			trip.getImgUrl(),
			trip.getCategory(),
			trip.getActive(),
			trip.getCreatedAt());
	}
}
			
			
