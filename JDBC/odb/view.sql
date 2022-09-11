create view most_popular_books as select b.name from orders as o
join books b on o.book_id = b.id
join authors a on b.author_id = a.id
group by b.name
having count(o.book_id) = (select count(orders.*) as c 
						  from orders 
						  group by orders.book_id
						  order by c desc
						  limit 1);


create or replace view most_popular_books as 
select b.name from orders as o
join books b on o.book_id = b.id
join authors a on b.author_id = a.id
group by b.name
having count(o.book_id) = (select count(orders.*) as c 
						  from orders 
						  group by orders.book_id
						  order by c desc
						  limit 1)
limit 1;

alter view most_popular_books rename to most_popular_book;