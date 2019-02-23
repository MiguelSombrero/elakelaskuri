
package elakelaskurisovellus.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Dao<T, K> {
    
    T create (T object) throws SQLException;
    T read (K key) throws SQLException;
    T update (T object) throws SQLException;
    void delete (K key) throws SQLException;
    List<T> list() throws SQLException;
}
