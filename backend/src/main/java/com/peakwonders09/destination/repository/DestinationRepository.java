package com.peakwonders09.destination.repository;

import com.peakwonders09.destination.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long>{
	List <Destination> findByActiveTrue();
	List <Destination> findByNameContainingIgnoreCase(String name);
}
