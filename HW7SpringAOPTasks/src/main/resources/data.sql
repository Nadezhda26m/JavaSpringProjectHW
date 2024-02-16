INSERT INTO tasks
VALUES (DEFAULT, 'Сходить в магазин', DEFAULT, '2024-02-06');

INSERT INTO tasks (description, status, create_date)
VALUES ('Приготовить ужин', DEFAULT, '2024-02-06 10:11:12');

INSERT INTO tasks (description, status, create_date)
VALUES ('Помыть посуду', DEFAULT, NOW());

INSERT INTO tasks (description, create_date)
VALUES ('Посмотреть фильм', NOW());
