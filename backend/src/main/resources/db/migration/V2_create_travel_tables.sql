CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	full_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	role VARCHAR(30) NOT NULL,
	enabled BOOLEAN NOT NULL DEFAULT TRUE,
	created_at TIMESTAMP NOT NULL);
	
CREATE TABLE destinations(
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	country VARCHAR(100),
	state VARCHAR(100),
	description TEXT,
	img_url VARCHAR(1000),
	enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE itineraries(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT NOT NULL,
	title VARCHAR(255) NOT NULL,
	start_date DATE,
	end_date DATE,
	
	CONSTRAINT fk_itinerary_user
		FOREIGN KEY(user_id)
		REFERENCES users(id)
);

CREATE TABLE itinerary_items(
	id BIGSERIAL PRIMARY KEY,
	itinerary_id BIGINT NOT NULL,
	day_number INTEGER,
	title VARCHAR(255),
	location VARCHAR(255),
	description TEXT,
	activity_type VARCHAR(100),
	
	CONSTRAINTS fk_item_itinerary
		FOREIGN KEY(itinerary_id)
		REFERENCES itineraries(id)
		ON DELETE CASCADE
);

CREATE TABLE bookings(
	id BIGSERIAL PRIMARY KEY,
	booking_reference VARCHAR(100) NOT NULL UNIQUE,
	user_id BIGINT NOT NULL,
	trip_id BIGNINT NOT NULL,
	travellers INTEGER NOT NULL,
	total_amount NUMERIC(10,2) NOT NULL,
	status VARCHAR(30),
	payment_status VARCHAR(30),
	created_at TIMESTAMP NOT NULL,
	
	CONSTRAINTS fk_booking_user
		FOREIGN KEY(user_id)
		REFERENCES users(id),
		
	CONSTRAINTS fk_booking_trip
		FOREIGN KEY(trip_id)
		REFERENCES trips(id)
);

CREATE TABLE wishlist(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT NOT NULL,
	trip_id BIGINT NOT NULL,
		
	CONSTRAINTS uq_user_trip
		UNIQUE(user_id,trip_id),
	
	CONSTRAINTS fk_wishlist_user
		FOREIGN KEY(user_id)
		REFERENCES trips(id)
);

CREATE TABLE reviews(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT NOT NULL,
	trip_id BIGINT NOT NULL,
	rating INTEGER NOT NULL,
	comment VARCHAR(1000),
	
	CONSTRAINTS chk_rating
		CHECK(rating BETWEEN 1 AND 5),
		
	CONSTRAINTS fk_review_user
		FOREIGN KEY(trip_id)
		REFERENCES users(id),
		
	CONSTRAINTS fk_review_trip
		FOREIGN KEY(trip_id)
		REFERENCES trips(id)
);
