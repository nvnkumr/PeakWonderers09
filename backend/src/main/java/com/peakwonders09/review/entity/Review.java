package com.peakwonders09.review.entity;

import com.peakwonders09.auth.entity.User;
import com.peakwonders09.trip.entity.Trip;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reviews")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	private User user;
	
	@ManyToOne(optional=false)
	private Trip trip;
	
	@Column(nullable =false)
	private Integer rating;
	
	@Column(length=1000)
	private String comment;
	
	/* use validation on the request
	 * @Min(1)
	 * Max(5)
	 * private Integer rating;
	 * */
}
