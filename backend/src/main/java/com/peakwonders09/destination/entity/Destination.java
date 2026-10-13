package com.peakwonders09.destination.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="destinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destination{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	private String country;
	private String state;
	private String description;
	private String imgUrl;
	private boolean active;
}
