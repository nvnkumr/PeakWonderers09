package com.peakwonders09.booking.entity;

import com.peakwonders09.auth.entity.User;
import com.peakwonders09.trip.entity.Trip;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="bookings")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String bookingReference;
	
	@ManyToOne(optional=false)
	private User user;
	
	@ManyToOne(optional=false)
	private Trip trip;
	
	@Column(nullable=false)
	private Integer travellers;
	
	@Column(nullable=false)
	private BigDecimal totalAmount;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
		
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	private LocalDateTime createdAt;
	
	public enum BookingStatus{
		PENDING,
		CONFIRMED,
		CANCELLED
	}
	
	public enum PaymentStatus{
		PENDING,
		SUCCESS,
		FAILED,
		REFUNDED
	}
	
	@PrePersist
	void onCreat(){
		createdAt=LocalDateTime.now();
		
		if(bookingStatus==null)
			bookingStatus =BookingStatus.PENDING;
		
		if(paymentStatus==null)
			paymentStatus=PaymentStatus.PENDING;
	}
}
