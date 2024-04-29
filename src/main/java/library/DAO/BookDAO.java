package library.DAO;

import library.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO implements DAO<Book> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void save(Book object) {
        jdbcTemplate.update("INSERT INTO books (name, author, year_of_release) VALUES (?, ?, ?)", object.getName(),
                object.getAuthor(), object.getYearOfRelease());
    }

    @Override
    public void update(long id, Book updatedObject) {
        jdbcTemplate.update("UPDATE books SET name = ?, author = ?, year_of_release = ? WHERE book_id = ?",
                updatedObject.getName(), updatedObject.getAuthor(), updatedObject.getYearOfRelease(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM books WHERE book_id = ?", id);
    }

    @Override
    public Book getElementById(long id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public List<Book> getBooksOfPerson(long personId){
        return jdbcTemplate.query("SELECT * FROM books WHERE person_id = ?", new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void addReader(long bookId, long selectedPersonId){
        jdbcTemplate.update("UPDATE books SET person_id = ? WHERE book_id = ?", selectedPersonId,
                bookId);
    }

    public void removeReader(long bookId){
        jdbcTemplate.update("UPDATE books SET person_id = null WHERE book_id = ?", bookId);
    }
}