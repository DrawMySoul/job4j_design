CREATE TABLE devices(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price FLOAT
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people(
    id SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices(id),
    people_id INT REFERENCES people(id)
);

INSERT INTO devices(name, price) VALUES ('phone', 497.26), ('robotic vacuum cleaner', 279.35), ('laptop', 771.45), ('drill', 168.10);
INSERT INTO people(name) VALUES ('Edie'), ('Gertrude'), ('Bethany'), ('Jack');

INSERT INTO devices_people(device_id, people_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4);
INSERT INTO devices_people(device_id, people_id) VALUES (2, 1), (2, 2), (2, 3);
INSERT INTO devices_people(device_id, people_id) VALUES (3, 1), (3, 3);
INSERT INTO devices_people(device_id, people_id) VALUES (4, 2), (4, 4);

SELECT AVG(devices.price) FROM devices;

SELECT p.name, AVG(d.price) FROM people AS p
INNER JOIN devices_people AS dp ON p.id = dp.people_id 
INNER JOIN devices AS d ON d.id = dp.device_id 
GROUP BY p.name;

SELECT p.name, AVG(d.price) FROM people AS p
INNER JOIN devices_people AS dp ON p.id = dp.people_id 
INNER JOIN devices AS d ON d.id = dp.device_id 
GROUP BY p.name
HAVING AVG(d.price) > 5000;