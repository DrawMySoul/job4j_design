--Добавьте процедуру и функцию, которая будет удалять записи.
create or replace procedure delete_date(d_id integer)
language 'plpgsql'
as $$
    BEGIN
    	if d_id > 0 THEN
			delete from products where id = d_id;
		end if;
    END
$$;


create or replace function f_delete_date(d_id integer)
returns varchar
language 'plpgsql'
as $$
	declare
		p_name varchar;
    BEGIN
    	if d_id > 0 THEN
			select into p_name name from products where id = d_id;
			delete from products where id = d_id;
		end if;
		return p_name;
    END
$$;