-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product
WHERE product.type_id = (SELECT type.id FROM type WHERE type.name = 'СЫР');

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT * FROM product 
WHERE product.name LIKE '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
SELECT * FROM product
WHERE product.expired_date < current_date;

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product AS p
WHERE p.price = (SELECT MAX(product.price) FROM product);

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
-- В виде имя_типа, количество
SELECT t.name AS type, COUNT(*) AS count FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
GROUP BY t.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.* FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО';

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.* FROM type AS t
INNER JOIN product AS p ON t.id = p.type_id
GROUP BY t.id, t.name
HAVING COUNT(p.*) < 10;

-- 8. Вывести все продукты и их тип.
SELECT p.name AS product, t.name AS type FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id;