
package elakelaskurisovellus.dao;

import elakelaskurisovellus.ElakelaskuriSovellus;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class LuoTietokantaDao {
    
    public void luoTietokanta () {
        try (Connection tietokanta = DriverManager.getConnection("jdbc:h2:./elakelaskuri", "sa", "")) {
            DatabaseMetaData meta = tietokanta.getMetaData();
            ResultSet taulu = meta.getTables(null, null, "ELAKEIKA", null);
            
            if (!taulu.next()) {
                ScriptUtils.executeSqlScript(tietokanta, new FileSystemResource("./luo_tietokanta.sql"));
                ScriptUtils.executeSqlScript(tietokanta, new FileSystemResource("./luo_data.sql"));
                System.out.println("Luotiin tietokanta eläkeikää ja elinaikakerrointa varten");
            }
        }
        catch (SQLException e) {
            Logger.getLogger(ElakelaskuriSovellus.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
