
package elakelaskurisovellus;

import elakelaskurisovellus.dao.LuoTietokantaDao;
import elakelaskurisovellus.kayttoliittyma.Tekstikayttoliittyma;
import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElakelaskuriSovellus implements CommandLineRunner {
    
    public static void main (String args[]) {
        SpringApplication.run(ElakelaskuriSovellus.class);
    }
    
    @Autowired
    Tekstikayttoliittyma kayttis;
    @Autowired
    LuoTietokantaDao tietokanta;

    @Override
    public void run(String... args) throws Exception {
        tietokanta.luoTietokanta();
        Scanner s = new Scanner(System.in);
        kayttis.kaynnista(s);
        
    }
    
}
