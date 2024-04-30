drop table books;
create table books(
    book_id serial8 primary key,
    person_id int8,
    name varchar(50) not null,
    author varchar(100) not null,
    year_of_release int4 check(year_of_release > 0 and year_of_release < 2025),
    foreign key (person_id) references people (person_id) on delete cascade
);

insert into books (person_id, name, author, year_of_release) VALUES (null, 'Убийство в восточном экспрессе', 'Агата Кристи', 1934),
                                                                    (null, 'Думай и богатей', 'Наполеон Хилл', 1937),
                                                                    (null, 'Богатый папа, бедный папа', 'Роберт Кийосаки', 1997),
                                                                    (null, '1984', 'Джордж Оруэлл', 1948),
                                                                    (null, '451 градус по Фаренгейту', 'Рэй Брэдбери', 1953),
                                                                    (null, 'Унесенные ветром', 'Маргарет Митчел', 1936),
                                                                    (null, 'Кладбище Домашних Животных', 'Стивен Кинг', 1983);