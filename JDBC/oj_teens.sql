-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
CREATE TABLE teens (
	name VARCHAR(255),
	gender VARCHAR(255)
);

INSERT INTO teens(name, gender) VALUES ('Hansiain', 'Male');
INSERT INTO teens(name, gender) VALUES ('Gun', 'Male');
INSERT INTO teens(name, gender) VALUES ('Henrietta', 'Female');
INSERT INTO teens(name, gender) VALUES ('Akim', 'Male');
INSERT INTO teens(name, gender) VALUES ('Idaline', 'Female');
INSERT INTO teens(name, gender) VALUES ('Danna', 'Female');
INSERT INTO teens(name, gender) VALUES ('Gerri', 'Male');

SELECT * FROM teens t1 CROSS JOIN teens t2
WHERE t1.gender != t2.gender;