create table pens(
    id serial primary key,
    manufacturer text,
    medium_point numeric,
    count integer
);
insert into pens(manufacturer, medium_point, count) values ('EricKrause', 0.3, 12);
update pens set medium_point = 0.5;
delete from pens;