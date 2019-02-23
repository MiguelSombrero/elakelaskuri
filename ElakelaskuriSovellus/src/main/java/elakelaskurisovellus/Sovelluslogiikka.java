
package elakelaskurisovellus;

import elakelaskurisovellus.dao.*;
import elakelaskurisovellus.domain.Elakeika;
import elakelaskurisovellus.domain.Elinaikakerroin;
import elakelaskurisovellus.domain.Laskelma;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sovelluslogiikka {
    
    @Autowired
    ElakeikaDao elakeika;
    @Autowired
    ElinaikakerroinDao elinaikakerroin;
    
    public Sovelluslogiikka () {
        
    }
    
    public void tarkistaElakeika (int vuosi) throws SQLException {
        System.out.println(elakeika.read(vuosi).toString());
    }
    
    public void laskeElakearviot (int vuosi, int kuukausi, int karttunut, int ansio) throws SQLException {
        Elakeika ika = elakeika.read(vuosi);
        Elinaikakerroin eak = elinaikakerroin.read(vuosi);
        
        for (int i = 0; i < 5; i++) {
            System.out.println("");
            Date alkamispaiva = annaElakkeenAlkamispaiva(vuosi, kuukausi, ika, i);
            Laskelma laskelma = laskeArvio(alkamispaiva, vuosi, kuukausi, karttunut, ansio, ika, eak, i);
            System.out.println(laskelma.toString());
        }
    }
    
    public Laskelma laskeArvio (Date alkamispaiva, int vuosi, int kuukausi, int karttunutElake, int ansio, Elakeika ika, Elinaikakerroin eak, int lykkaysvuodet) {
        Period tastaElakeikaan = Period.between(LocalDate.now(), alkamispaiva.toLocalDate());
        int vuosiaElakeikaan = tastaElakeikaan.getYears();
        int kuukausiaElakeikaan = tastaElakeikaan.getMonths();
        
        // lasketaan arvioidun eläkkeen määrä
        double arvioituElake = ansio * 0.015 * vuosiaElakeikaan * 1.02 + ansio * 0.015 * kuukausiaElakeikaan/12;
        // summataan arvioitu ja karttunut eläke
        int elakeYhteensa = (int) arvioituElake + karttunutElake;
        // lasketaan mahdollinen lykkäyskorotus
        elakeYhteensa += lykkaysvuodet * 12 * 0.004 * elakeYhteensa;
        
        // lasketaan elinaikakerroinmuunto
        elakeYhteensa *= eak.getElinaikakerroin();
        
        return new Laskelma("Vanhuuseläke", alkamispaiva, elakeYhteensa);
    }
    
    public Date annaElakkeenAlkamispaiva (int vuosi, int kuukausi, Elakeika ika, int i) {
        int alkamisVuosi = vuosi + ika.getVuodet() + i;
        int alkamisKuukausi = kuukausi + ika.getKuukaudet()+1;
        
        if (alkamisKuukausi > 12) {
            alkamisVuosi++;
            alkamisKuukausi /= 12;
        }
        
        return Date.valueOf(alkamisVuosi + "-" + alkamisKuukausi + "-01");
    }
    
}
