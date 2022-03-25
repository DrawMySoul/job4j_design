create table customers(
	id serial primary key,
	name varchar(255)
)

create table bank_card(
	id serial primary key,
	number int,
	customer_id references customer(id)
)