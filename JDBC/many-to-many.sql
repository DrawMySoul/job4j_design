create table social_networks(
	id serial primary key,
	name varchar(255)
);

create table users(
	id serial primary key,
	name varchar(255)
);

create table users_social_networks(
	id serial primary key,
	user_id int references users(id),
	social_network_id int references social_networks(id)
);