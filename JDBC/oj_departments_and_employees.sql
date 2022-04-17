-- 1. Создать таблицы и заполнить их начальными данными
CREATE TABLE departments (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE employees(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	department_id INT REFERENCES departments(id)
);

INSERT INTO departments(name) VALUES ('sales'), ('legal'), ('marketing');
INSERT INTO employees(name, department_id) VALUES ('Tom', 1);
INSERT INTO employees(name, department_id) VALUES ('Henry', 1);
INSERT INTO employees(name, department_id) VALUES ('Toby', 2);
INSERT INTO employees(name, department_id) VALUES ('John', null);

-- 2. Выполнить запросы с left, rigth, full, cross соединениями
SELECT * FROM employees
LEFT JOIN departments ON employees.department_id = departments.id;

SELECT * FROM departments
RIGHT JOIN employees ON departments.id = employees.department_id;

SELECT * FROM employees
FULL JOIN departments ON employees.department_id = departments.id;

SELECT * FROM employees CROSS JOIN departments;

-- 3. Используя left join найти департаменты, у которых нет работников
SELECT d.* FROM departments AS d
LEFT JOIN employees AS e ON d.id = e.department_id
WHERE e.id IS NULL;

-- предпочтительный вариант присоединения 
SELECT DISTINCT d.* FROM employees AS e
LEFT JOIN departments AS d ON d.id != e.department_id
WHERE d.id = (SELECT d.id FROM employees AS e 
						JOIN departments AS d ON e.department_id != d.id
						GROUP BY d.id 
						ORDER BY d.id DESC LIMIT 1);

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный). 
SELECT * FROM employees AS e
LEFT JOIN departments AS d ON e.department_id = d.id;

SELECT e.*, d.* FROM departments AS d
RIGHT JOIN employees AS e ON d.id = e.department_id;