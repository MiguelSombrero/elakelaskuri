
package elakelaskurisovellus.dao;

import elakelaskurisovellus.domain.*;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            return yhteys.queryForObject(
                "SELECT * FROM Elinaikakerroin WHERE syntymavuosi = ?",
                new BeanPropertyRowMapper<>(Elinaikakerroin.class), key
            );
        }
        catch (DataAccessException e) {
            System.out.println("Elinaikakerrointa ei löytynyt tietokannasta");
            System.out.println("Käytetään elinaikakerrointa 1,00000");
            return new Elinaikakerroin(key, 1.00000, false);
        }
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
