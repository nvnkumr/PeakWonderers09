package com.peakwonders09.itinerary.entity;

import com.peakwonders09.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="itinerary")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	private User user;
	
	@Column(nullable=false)
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@OneToMany(
		mappedBy="itinerary",
		cascade=CascadeType.ALL,
		orphanRemoval=true)
	@Builder.Default
	private List<ItineraryItem> items=new ArrayList<>();
	
}
