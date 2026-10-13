package com.peakwonders09.wishlist.entity;

import com.peakwonders09.auth.entity.User;
import com.peakwonders09.trip.entity.Trip;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="wishlists",
	uniqueConstraints=@UniqueConstraint(
		columnNames={"user_id","trip_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	private User user;
	
	@ManyToOne(optional=false)
	private Trip trip;
}
//this prevents the same user from adding the same trip twice
