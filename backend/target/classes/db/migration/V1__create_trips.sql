CREATE TABLE trips(
	id BIGSERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	destination VARCHAR(255) NOT NULL,
	description VARCHAR(2000),
	duration_days INTEGER NOT NULL,
	price NUMERIC(10,2) NOT NULL,
	img_url VARCHAR(1000),
	active BOOLEAN NOT NULL DEFAULT TRUE,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL);
	
CREATE INDEX idx_trips_destination ON trips(destination);

CREATE INDEX idx_trips_category ON trips(category);

CREATE INDEX idx_trips_active ON trips(active);

