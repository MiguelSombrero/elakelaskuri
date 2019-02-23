
package elakelaskurisovellus.dao;

import elakelaskurisovellus.domain.Elakeika;
import elakelaskurisovellus.domain.Elinaikakerroin;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ElinaikakerroinDao implements Dao<Elinaikakerroin, Integer> {

    @Autowired
    JdbcTemplate yhteys;
    
    @Override
    public Elinaikakerroin create(Elinaikakerroin object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Elinaikakerroin read(Integer key) throws SQLException {
        return yhteys.queryForObject(
                "SELECT * FROM Elinaikakerroin WHERE syntymavuosi = ?",
                new BeanPropertyRowMapper<>(Elinaikakerroin.class), key
        );
    }

    @Override
    public Elinaikakerroin update(Elinaikakerroin object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Elinaikakerroin> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
