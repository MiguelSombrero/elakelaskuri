
package elakelaskurisovellus;

import elakelaskurisovellus.dao.LuoTietokantaDao;
import elakelaskurisovellus.kayttoliittyma.Tekstikayttoliittyma;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElakelaskuriSovellus implements CommandLineRunner {
    
    @Autowired
    Tekstikayttoliittyma kayttis;
    @Autowired
    LuoTietokantaDao tietokanta;

    public static void main (String args[]) {
        SpringApplication.run(ElakelaskuriSovellus.class);
    }
    
    @Override
    public void run(String... args) throws Exception {
        tietokanta.luoTietokanta();
        Scanner s = new Scanner(System.in);
        kayttis.kaynnista(s);
        
    }
    
}
