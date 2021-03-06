
package elakelaskurisovellus.dao;

import elakelaskurisovellus.domain.Elakeika;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ElakeikaDao implements Dao<Elakeika, Integer> {
    
    @Autowired
    JdbcTemplate yhteys;

    HashMap<Integer, Elakeika> elakeiat = new HashMap<>();
    
    @Override
    public Elakeika create(Elakeika object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Elakeika read(Integer key) throws SQLException {
        if (!elakeiat.containsKey(key)) {
            try {
                elakeiat.put(key, yhteys.queryForObject(
                    "SELECT * FROM Elakeika WHERE syntymavuosi = ?",
                    new BeanPropertyRowMapper<>(Elakeika.class), key
                ));
            }
            catch (DataAccessException e) {
                System.out.println("Arvioitua eläkeikää ei löytynyt tietokannasta");
            
                if (key < 1954) {
                    System.out.println("Käytetään eläkeikänä 63 vuotta");
                    elakeiat.put(key, new Elakeika(key, 63, 0, 68, false));
                }
                else {
                    System.out.println("Käytetään eläkeikänä 68 vuotta");
                    elakeiat.put(key, new Elakeika(key, 68, 0, 70, false));
                }
            }
        }
        
        return elakeiat.get(key);
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
