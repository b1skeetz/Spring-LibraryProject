package library.DAO;

import library.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO implements DAO<Person> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll(){
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getElementById(long id){
        return jdbcTemplate.query("SELECT * FROM people p WHERE p.person_id = ?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO people (self_identity, year_of_birth) values (?, ?)", person.getSelfIdentity(),
                person.getYearOfBirth());
    }

    public void update(long id, Person updatedPerson){
        jdbcTemplate.update("UPDATE people SET self_identity = ?, year_of_birth = ? WHERE person_id = ?", updatedPerson.getSelfIdentity(),
                updatedPerson.getYearOfBirth(), id);
    }

    public void delete(long id){
        jdbcTemplate.update("DELETE FROM people WHERE person_id = ?", id);
    }
}
