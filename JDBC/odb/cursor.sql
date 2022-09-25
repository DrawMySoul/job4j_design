begin; 
	declare products_cur cursor for select * from products;
	
		move forward 21 from products_cur;
		fetch backward 5 from products_cur;
		move backward 5 from products_cur;
		fetch prior from products_cur;
		fetch 5 from products_cur;
		move backward 5 from products_cur;
		fetch backward 9 products_cur;
		fetch first from products_cur;
	close products_cur;
commit;