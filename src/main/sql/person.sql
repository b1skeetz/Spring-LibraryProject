create table People(
    person_id serial8 primary key,
    self_identity varchar(100) not null unique,
    year_of_birth int4 check(year_of_birth > 1900 and year_of_birth < 2024)
);

insert into People (self_identity, year_of_birth) VALUES ('Терентьева Кристина Александровна', 2002),
                                                    ('Захаров Михаил Сергеевич', 1995),
                                                    ('Крылов Максим Александрович', 1988),
                                                    ('Орехова Вероника Макаровна', 2014),
                                                    ('Воробьева Юлия Александровна', 2004),
                                                    ('Федоров Арсений Максимович', 1973),
                                                    ('Константинова Эмилия Давидовна', 1961);