insert into roles (role_name) values ('admin');
insert into roles (role_name) values ('moderator');
insert into roles (role_name) values ('user');
insert into roles (role_name) values ('banned_user');

insert into rules (rule_name) values ('UPDATE');
insert into rules (rule_name) values ('DELETE');
insert into rules (rule_name) values ('CHANGE_STATUS');
insert into rules (rule_name) values ('BAN');
insert into rules (rule_name) values ('UNBAN');
insert into rules (rule_name) values ('WRITE_TO_CHAT');
insert into rules (rule_name) values ('CREATE_ITEM');
insert into rules (rule_name) values ('ADD_ATTACHE');
insert into rules (rule_name) values ('ADD_COMMENT');

insert into role_rules (role_id, rule_id) values (1, 1);
insert into role_rules (role_id, rule_id) values (1, 2);
insert into role_rules (role_id, rule_id) values (1, 3);
insert into role_rules (role_id, rule_id) values (1, 4);
insert into role_rules (role_id, rule_id) values (1, 5);
insert into role_rules (role_id, rule_id) values (1, 6);
insert into role_rules (role_id, rule_id) values (1, 7);
insert into role_rules (role_id, rule_id) values (1, 8);
insert into role_rules (role_id, rule_id) values (1, 9);

insert into role_rules (role_id, rule_id) values (2, 3);
insert into role_rules (role_id, rule_id) values (2, 4);
insert into role_rules (role_id, rule_id) values (2, 5);
insert into role_rules (role_id, rule_id) values (2, 6);
insert into role_rules (role_id, rule_id) values (2, 7);
insert into role_rules (role_id, rule_id) values (2, 8);
insert into role_rules (role_id, rule_id) values (2, 9);

insert into role_rules (role_id, rule_id) values (3, 6);
insert into role_rules (role_id, rule_id) values (3, 7);
insert into role_rules (role_id, rule_id) values (3, 8);
insert into role_rules (role_id, rule_id) values (3, 9);

insert into role_rules (role_id, rule_id) values (4, 7);
insert into role_rules (role_id, rule_id) values (4, 8);
insert into role_rules (role_id, rule_id) values (4, 9);

insert into users (nickname, role_id) values ('SuperAdmin', 1);
insert into users (nickname, role_id) values ('Razer007', 2);
insert into users (nickname, role_id) values ('Yamaoka', 2);
insert into users (nickname, role_id) values ('HoneyMad', 3);
insert into users (nickname, role_id) values ('Melharucos', 3);
insert into users (nickname, role_id) values ('qwerty', 3);
insert into users (nickname, role_id) values ('Mr.Orange', 4);

insert into categories (category_name) values ('BAN');
insert into categories (category_name) values ('UNBAN');
insert into categories (category_name) values ('DELETE');
insert into categories (category_name) values ('UPDATE');

insert into statuses (status_name) values ('IN_PROGRESS');
insert into statuses (status_name) values ('DONE');

insert into items (category_id, discription, user_id, status_id) 
values (4, 'Добрый день, прошу изменить мой ник с qwerty на qwerty1234', 6, 1);
insert into items (category_id, discription, user_id, status_id) 
values (1, 'Здравствуйте, забаньте пользователя HoneyMad, он нарушает правила чата. Скрин приложил', 5, 1);
insert into items (category_id, discription, user_id, status_id ) 
values (2, 'Разбаньте я буду вести себя хорошо!!!!', 7, 1);

insert into attaches (path_to_file, item_id) values ('\items\screen_shots\item_id2.png', 2);

insert into comments (comment, item_id)
values ('Добрый день, спасибо за ваше обращение, ваша заявка будет распотрена в течении 24 часов', 2);