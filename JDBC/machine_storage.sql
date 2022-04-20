CREATE TABLE carbodies(
	id SERIAL PRIMARY KEY,
	type VARCHAR(255)
);

CREATE TABLE engines(
	id SERIAL PRIMARY KEY,
	type VARCHAR(255)
);

CREATE TABLE gearboxes(
	id SERIAL PRIMARY KEY,
	type VARCHAR(255)
);

CREATE TABLE cars(
	id SERIAL PRIMARY KEY,
	name text,
	carbody_id INT REFERENCES carbodies(id),
	engine_id INT REFERENCES engines(id),
	gearbox_id INT REFERENCES gearboxes(id)
);

INSERT INTO carbodies(type) VALUES ('crossover'), ('pickup'), ('sedan'), ('van'), ('coupe');
INSERT INTO engines(type) VALUES ('petrol'), ('deisel'), ('electric');
INSERT INTO gearboxes(type) VALUES ('manual'), ('automatic'), ('robot');
INSERT INTO cars(name, carbody_id, engine_id, gearbox_id) VALUES ('Volkswagen Tiguan', 1, 1, 2);
INSERT INTO cars(name, carbody_id, engine_id, gearbox_id) VALUES ('Toyota Hilux', 2, 2, 2);
INSERT INTO cars(name, carbody_id, engine_id, gearbox_id) VALUES ('Kia Rio', 3, NULL, 1);
INSERT INTO cars(name, carbody_id, engine_id, gearbox_id) VALUES ('Mercedes-Benz Vito', 4, 1, NULL);
INSERT INTO cars(name, carbody_id, engine_id, gearbox_id) VALUES ('Imagine Car', NULL, NULL, NULL);

SELECT c.name AS car, cb.type AS carbody, e.type AS engine, gb.type AS gearbox FROM cars c
LEFT JOIN carbodies cb ON c.carbody_id = cb.id
LEFT JOIN engines e ON c.engine_id = e.id
LEFT JOIN gearboxes gb ON c.gearbox_id = gb.id;

SELECT cb.* FROM carbodies cb
LEFT JOIN cars c ON cb.id = c.carbody_id
WHERE c.carbody_id IS NULL;

SELECT e.* FROM engines e
LEFT JOIN cars c ON e.id = c.engine_id
WHERE c.engine_id IS NULL;

SELECT gb.* FROM gearboxes gb
LEFT JOIN cars c ON gb.id = c.gearbox_id
WHERE c.gearbox_id IS NULL;