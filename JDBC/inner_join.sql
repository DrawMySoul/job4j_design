CREATE TABLE director(
	id SERIAL PRIMARY KEY,
	full_name TEXT
);

CREATE TABLE film(
	id SERIAL PRIMARY KEY,
	director_id INT REFERENCES director(id),
	title VARCHAR(255),
	release_year INT
);

INSERT INTO director(full_name) VALUES ('Coen brothers');
INSERT INTO director(full_name) VALUES ('Paul Thomas Anderson');

INSERT INTO film(director_id, title, release_year) 
VALUES (1, 'No Country For Old Men', 2007);
INSERT INTO film(director_id, title, release_year) 
VALUES (1, 'Inside Llewyn Davis', 2013);
INSERT INTO film(director_id, title, release_year) 
VALUES (2, 'There Will Be Blood', 2007);
INSERT INTO film(director_id, title, release_year) 
VALUES (2, 'The Master', 2012);

SELECT d.full_name AS director, f.title, f.release_year AS year FROM film AS f
INNER JOIN director AS d ON f.director_id = d.id;

SELECT d.full_name AS name, f.title, f.release_year AS year FROM film AS f
INNER JOIN director AS d ON f.director_id = d.id
WHERE f.release_year = 2007;

SELECT d.full_name AS name, f.title, f.release_year AS year FROM film AS f
INNER JOIN director AS d ON f.director_id = d.id
WHERE d.full_name LIKE '%th%';