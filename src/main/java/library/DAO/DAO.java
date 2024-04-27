package library.DAO;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    void save(T object);
    void update(long id, T updatedObject);
    void delete(long id);
    T getElementById(long id);
}
