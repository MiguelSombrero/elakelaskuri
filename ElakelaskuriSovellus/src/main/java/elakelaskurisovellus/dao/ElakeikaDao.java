
package elakelaskurisovellus.dao;

import elakelaskurisovellus.domain.Elakeika;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ElakeikaDao implements Dao<Elakeika, Integer> {
    
    @Autowired
    JdbcTemplate yhteys;

    @Override
    public Elakeika create(Elakeika object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Elakeika read(Integer key) throws SQLException {
        if (key < 1954) return new Elakeika(key, 63, 0, false);
        else if (key > 1997) return new Elakeika(key, 68, 0, false);
        
        return yhteys.queryForObject(
                "SELECT * FROM Elakeika WHERE syntymavuosi = ?",
                new BeanPropertyRowMapper<>(Elakeika.class), key
        );
    }

    @Override
    public Elakeika update(Elakeika object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Elakeika> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
