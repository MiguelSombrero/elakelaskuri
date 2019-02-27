
package elakelaskurisovellus.kayttoliittyma;

import elakelaskurisovellus.Sovelluslogiikka;
import elakelaskurisovellus.domain.Henkilo;
import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Tekstikayttoliittyma {
    
    @Autowired
    Sovelluslogiikka laskuri;
    
    public void kaynnista (Scanner s) throws SQLException {
        System.out.println("ELÄKELASKURI 1.0");
        kaynnistaLaskuri(s);
    }
    
    public void kaynnistaLaskuri (Scanner s) throws SQLException {
        while (true) {
            tulostaToiminnot();
            String komento = s.nextLine();
        
            if (komento.equals("x")) {
                break;
            }
            else if (komento.equals("1")) {
                laskeElakeArvio(s);
            }
            else if (komento.equals("2")) {
                tarkistaElakeika(s);
            }
            else System.out.println("Väärä komento!");
        }
    }
    
    public void laskeElakeArvio (Scanner s) throws SQLException {
        System.out.println("Anna syntymävuosi: [vvvv]");
        int vuosi = Integer.valueOf(s.nextLine());
        System.out.println("Anna syntymäkuukausi: [kk]");
        int kuukausi = Integer.valueOf(s.nextLine());
        System.out.println("Anna viime vuoden loppuun mennessä karttunut eläke euroina (työeläkeotteelta): ");
        int karttunut = Integer.valueOf(s.nextLine());
        System.out.println("Anna kuukausipalkkasi euroina (brutto): ");
        int ansiot = Integer.valueOf(s.nextLine());
        
        laskuri.laskeElakearviot(new Henkilo(vuosi, kuukausi, karttunut, ansiot));
    }
    
    public void tarkistaElakeika (Scanner s) throws SQLException {
        System.out.println("Anna syntymävuosi: [vvvv]");
        int vuosi = Integer.valueOf(s.nextLine());
        laskuri.tarkistaElakeika(vuosi);
    }
    
    public void tulostaToiminnot () {
        System.out.println("x - lopeta");
        System.out.println("1 - laske eläkearvio");
        System.out.println("2 - tarkista eläkeikä");
        System.out.println("");
    }
    
}
