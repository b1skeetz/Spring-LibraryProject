package library.DAO;

import library.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO implements DAO<Person> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Person getElementById(long id) {
        return jdbcTemplate.query("SELECT * FROM people p WHERE p.person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO people (self_identity, year_of_birth) values (?, ?)",
                person.getSelfIdentity(), person.getYearOfBirth());
    }

    @Override
    public void update(long id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE people SET self_identity = ?, year_of_birth = ? WHERE person_id = ?",
                updatedPerson.getSelfIdentity(), updatedPerson.getYearOfBirth(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM people WHERE person_id = ?", id);
    }

    public Optional<Person> getPersonWithBook(long bookId) {
        return jdbcTemplate.query("SELECT * FROM people join public.books b on people.person_id = b.person_id " +
                        "WHERE b.book_id = ?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public Optional<Person> getPersonBySelfIdentity(String selfIdentity) {
        return jdbcTemplate.query("SELECT * FROM people WHERE self_identity = ?", new Object[]{selfIdentity},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}