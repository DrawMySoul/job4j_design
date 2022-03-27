create table roles(
	id serial primary key,
	role_name varchar(255)
);

create table rules(
	id serial primary key,
	rule_name varchar(255)
);

create table role_rules(
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);

create table users(
	id serial primary key,
	nickname varchar(255),
	role_id int references roles(id)
);

create table categories(
	id serial primary key,
	category_name varchar(255)
);

create table statuses(
	id serial primary key,
	status_name varchar(255)
);

create table items(
	id serial primary key,
	category_id int references categories(id),
	discription text,
	user_id int references users(id),
	status_id int references statuses(id)
);

create table comments(
	id serial primary key,
	comment text,
	item_id int references items(id)
);

create table attaches(
	id serial primary key,
	path_to_file text,
	item_id int references items(id)
);