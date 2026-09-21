package com.peakwonders09.repository;

import com.peakwonders09.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository <Trip, Long>{
	Page<Trip> findByActiveTrue(Pageable pageable);
	
	Page<Trip> findByDestinationContainingIgnoreCase(String destinaton, Pageable pageable);
	
	Page<Trip> findByCategoryIgnoreCase(String category, Pageable pageable);
}

/*
* jpaRepos extends so spring automatically gives some methods as save()
,findById(),findAll(),deleteById().. 

* page and pagable used for pagination- returning db results in smaller 
chunks instead of loading evrything at once.
* page<T> repres the result of that paginated query.(contains content,totalements,totalpages,
page number,size,first and last.
* pagable repres what page user wants and how many records per page.
*/
