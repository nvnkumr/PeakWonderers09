package com.peakwonders09.itinerary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="itinerary_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItem{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="itinerary_id")
	private Itinerary itinerary;
	
	private Integer dayNumber;
	private String title;
	private String location;
	private String description;
	private String activityType;
}
