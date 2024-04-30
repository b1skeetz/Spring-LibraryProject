select p.self_identity, b.name from people p left join public.books b on p.person_id = b.person_id;

delete from books b where b.book_id = 8;