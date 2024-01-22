CREATE TABLE IF NOT EXISTS userTable (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);

INSERT INTO userTable(firstName, lastName) VALUES ('Иван', 'Иванов');
INSERT INTO userTable(firstName, lastName) VALUES ('Василий', 'Петров');
INSERT INTO userTable(firstName, lastName) VALUES ('Юлия', 'Сурикова');