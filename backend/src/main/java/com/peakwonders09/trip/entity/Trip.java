package com.peakwonders09.trip.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity  //tells jpa that trip should become a databse table
@Table(name="trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //creates objs use a readable builder pattern
public class Trip{
	
	@Id //identifies the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)// postgresql generates the id automatically
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String destination;
	
	@Column(length = 2000)
	private String description;
	
	@Column(nullable = false)
	private Integer durationDays;
	
	@Column(nullable = false, precision=10, scale=2)//prcision=10 means max total digit and scale=2 is digits after decimal
	private BigDecimal price;
	
	private String imgUrl;
	private String category;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
	protected void onCreate(){
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
		
		if(active == null){
			active = true;
		}
	}
	
	@PreUpdate
	protected void onUpdate(){
		updatedAt = LocalDateTime.now();
	}
}
