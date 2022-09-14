-- 1)  Триггер должен срабатывать после вставки данных, для любого товара и
--просто насчитывать налог на товар (нужно прибавить налог к цене товара).
--Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create trigger nalog_after_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure add_nalog_after();

create or replace function add_nalog_after()
    returns trigger as
$$
	BEGIN
		update products 
		set price = price + price * 0.2
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';


--2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар 
--(нужно прибавить налог к цене товара). Здесь используем row уровень.
create trigger nalog_before_trigger
	before insert on products
	for each row
	execute procedure add_nalog_before();

create or replace function add_nalog_before()
    returns trigger as
$$
	BEGIN
		new.price = new.price + new.price * 0.2;
		return new;
	END;
$$
LANGUAGE 'plpgsql';

--3)Нужно написать триггер на row уровне, который при вставке продукта в таблицу products,
--будет заносить имя, цену и текущую дату в таблицу history_of_price.
create trigger history_trigger
	after insert on products
	for each row
	execute procedure add_to_history();

create or replace function add_to_history()
    returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date)
		values(new.name, new.price, now());
		return new;
	END;
$$
LANGUAGE 'plpgsql';