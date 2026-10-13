package com.peakwonders09.booking.service;

import com.peakwonders09.auth.entity.User;
import com.peakwonders09.auth.repository.UserRepository;
import com.peakwonders09.booking.entity.Booking;
import com.peakwonders09.booking.repository.BookingRepository;
import com.peakwonders09.trip.entity.Trip;
import com.peakwonders09.trip.repository.TripRepository;

import lombok.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService{
	private final BookingRepository bookingRepository;
	private final TripRepository tripRepository;
	private final UserRepository userRepository;
	
	public Booking createBooking(Long userId, Long tripId, int travellers){
		User user=userRepository.findById(userId)
			.orElseThrow();
			
		Trip trip=tripRepository.findById(tripId)
			.orElseThrow();
			
		if(!trip.getActive()){
			throw new IllegalStateException("Trip is not Available");
		}
		
		BigDecimal total = trip.getPrice().multiply(BigDecimal.valueOf(travellers));
		
		Booking booking=Booking.builder()
			.bookingReference(
				"PW09-"+UUID.randomUUID()
				.toString()
				.substring(0,8)
				.toUpperCase())
			.user(user)
			.trip(trip)
			.travellers(travellers)
			.totalAmount(total)
			.bookingStatus(Booking.BookingStatus.PENDING)
			.paymentStatus(Booking.PaymentStatus.PENDING)
			.build();
			
		return bookingRepository.save(booking);
	}
}
