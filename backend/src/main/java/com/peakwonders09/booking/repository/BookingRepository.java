package com.peakwonders09.booking.repository;

import com.peakwonders09.booking.entity.Booking;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	Page<Booking> findByUserId(Long userId, Pageable pageable);
	
	boolean existByBookingReference(String bookingReference);
	
	List<Booking> findByUserIdAndStatus(Long userId, Booking.BookingStatus status);
}
