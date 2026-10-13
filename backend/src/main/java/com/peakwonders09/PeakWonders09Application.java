package com.peakwonders09;//pkg-> where the class belongs

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;//allows boot to start

/* EnableCaching for redis cache */
@SpringBootApplication //tells sb this is main application class
public class PeakWonders09Application{
	public static void main(String[] args){
		SpringApplication.run(PeakWonders09Application.class, args); //start sb application
	}
	
	/* cache trip queries 
	 * @Cacheable(value="trips",key="#id")
	 * 
	 * @Transactional(readOnly=true)
	 * public TripResponse getById(Long id){
	 * 	Trip trip=tripRepository.findById(id)
	 * 		.orElseThrow();
	 * 	return toresponse(trip);
	 * }
	 * 
	 * when updating
	 * @CacheEvict(value="trips",key="#id")
	 * public TripResponse update(Long id, TripRequest req){
	 * 	//update
	 * }
	 * */
}
