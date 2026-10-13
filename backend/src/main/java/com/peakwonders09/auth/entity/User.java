package com.peakwonders09.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =false)
	private String fullName;
	
	@Column(nullable =false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Role role;
	
	private boolean enabled;
	private LocalDateTime createdAt;
	
	@PrePersist
	void create(){
		createdAt = LocalDateTime.now();
		
		if(role==null){
			role=Role.CUSTOMER;
		}
		enabled = true;
	}
	
	public enum Role{
		CUSTOMER,ADMIN
	}
}
