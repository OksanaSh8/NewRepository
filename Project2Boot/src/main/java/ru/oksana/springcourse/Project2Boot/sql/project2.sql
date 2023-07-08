CREATE TABLE Person (
                        id int auto_increment PRIMARY KEY,
                        full_name varchar(100) NOT NULL UNIQUE,
                        year_of_birth int NOT NULL,
                        password varchar (100) NOT NULL,
                        role varchar (20) NOT NULL
);

CREATE TABLE Book (
                      id int auto_increment PRIMARY KEY,
                      title varchar(100) NOT NULL,
                      author varchar(100) NOT NULL,
                      year int NOT NULL,
                      person_id int REFERENCES Person(id) ON DELETE SET NULL,
                      created_at timestamp
);

INSERT INTO book(title, author, year) VALUES ('Над пропастью во ржи','Джером Сэлинджер',1951);
INSERT INTO book(title, author, year) VALUES ('День опричника','Владимир Сорокин',2006);
INSERT INTO book(title, author, year) VALUES ('Тайные виды на гору Фудзи','Владимир Пелевин',2019);
INSERT INTO book(title, author, year) VALUES ('Философия Java','Брюс Эккель',2018);
INSERT INTO book(title, author, year) VALUES ('Психопатология обыденной жизни','Фрейд Зигмунд',1904);
INSERT INTO book(title, author, year) VALUES ('Игра в бисер','Герман Гессе',1943);
INSERT INTO book(title, author, year) VALUES ('Бытие и время','Мартин Хайдеггер',1927);
INSERT INTO book(title, author, year) VALUES ('Жизнь взаймы','Эрих Мария Ремарк',1989);