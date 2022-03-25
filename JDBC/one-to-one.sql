create table vin_code(
	id serial primary key, 
	VINcode int
);

create table cars(
	id serial primary key,
	manufacture varchar(255),
	model varchar(255),
	vin_code_id references vin_code(id) unique
)