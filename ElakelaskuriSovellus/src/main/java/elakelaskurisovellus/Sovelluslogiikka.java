
package elakelaskurisovellus;

import elakelaskurisovellus.dao.*;
import elakelaskurisovellus.domain.Elakeika;
import elakelaskurisovellus.domain.Elinaikakerroin;
import elakelaskurisovellus.domain.Laskelma;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
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
        List<Laskelma> laskelmat = annaLaskelmat(3, vuosi, kuukausi, ika);
        
        System.out.println(ika.toString());
        
        for (Laskelma laskelma : laskelmat) {
            System.out.println("");
            laskeArvio(laskelma, vuosi, kuukausi, karttunut, ansio, ika, eak);
            System.out.println(laskelma.toString());
        }
    }
    
    public void laskeArvio (Laskelma laskelma, int vuosi, int kuukausi, int karttunutElake, int ansio, Elakeika ika, Elinaikakerroin eak) {
        Period tastaAlkamiseen = Period.between(LocalDate.now(), laskelma.getAlkamispaiva().toLocalDate());
        int kuukausiaAlkamiseen = 12 * tastaAlkamiseen.getYears() + tastaAlkamiseen.getMonths();
        int kk17 = annaKuukaudet17karttumaan(vuosi, kuukausi);
        
        // lasketaan arvioidun eläkkeen määrä 1,7 % vuodessa, ansion korotus 2% vuodessa
        double arvioituElake17 = ansio * 0.017 * kk17 / 12;
        
        // lasketaan arvioidun eläkkeen määrä 1,5 % vuodessa, ansion korotus 2% vuodessa
        double arvioituElake15 = ansio * 0.015 * (kuukausiaAlkamiseen-kk17) / 12;
        
        // otetaan karttuneesta eläkkeestä pois elinaikakertoimen vaikutus, koska eak muunto tehdään vasta lykkäyskorotuksen jälkeen
        karttunutElake /= eak.getElinaikakerroin();
        
        // summataan arvioitu ja karttunut eläke
        double elakeYhteensa = arvioituElake15 + arvioituElake17 + karttunutElake;
        
        // lasketaan mahdollinen lykkäyskorotus 0,4% kuukaudessa koko eläkkeeseen
        elakeYhteensa += laskelma.getLykkayskuukaudet() * 0.004 * elakeYhteensa;
        
        // lasketaan elinaikakerroinmuunto koko eläkkeeseen
        elakeYhteensa *= eak.getElinaikakerroin();
        
        laskelma.setMaara((int) elakeYhteensa);
    }
    
    public List<Laskelma> annaLaskelmat (int montako, int vuosi, int kuukausi, Elakeika ika) {
        List<Laskelma> laskelmat = new ArrayList<>();
        
        // pura tämä ja käytä annaIanTayttamipaiva metodia, mutta ole tarkkana!
        int alkamisVuosi = Math.max(vuosi + ika.getVuodet(), LocalDate.now().getYear());
        int alkamisKuukausi = Math.max(kuukausi + ika.getKuukaudet() + 1, LocalDate.now().getMonthValue());
        
        if (alkamisKuukausi > 12) {
            alkamisVuosi++;
            alkamisKuukausi /= 12;
        }
        
        for (int i = 0; i < montako; i++) {
            Date alkamispaiva = Date.valueOf((alkamisVuosi + i) + "-" + alkamisKuukausi + "-01");
            int lykkayskuukaudet = annaLykkayskuukaudet(alkamisVuosi+i, alkamisKuukausi, vuosi, kuukausi, ika);
            int ikaVuodet = ika.getVuodet() + lykkayskuukaudet/12;
            int ikaKuukaudet = ika.getKuukaudet() + lykkayskuukaudet%12 - 1;
            laskelmat.add(new Laskelma("Vanhuuseläke", alkamispaiva, 0, lykkayskuukaudet, ikaVuodet, ikaKuukaudet));
        }
            
        return laskelmat;
    }
    
    public int annaLykkayskuukaudet (int alkamisvuosi, int alkamiskuukausi, int vuosi, int kuukausi, Elakeika ika) {
        return 12 * alkamisvuosi + alkamiskuukausi - (12 * vuosi + kuukausi + 12 * ika.getVuodet() + ika.getKuukaudet());
    }
    
    public int annaKuukaudet17karttumaan(int syntymavuosi, int syntymakuukausi) {
        Date tayttamispaiva53 = annaIanTayttamispaiva(syntymavuosi, syntymakuukausi, 53, 0);
        Date tayttamispaiva63 = annaIanTayttamispaiva(syntymavuosi, syntymakuukausi, 63, 0);
        Date kuluva = Date.valueOf(LocalDate.now());
        Date alku = tayttamispaiva53;
        Date loppu = Date.valueOf("2025-01-01");
        
        if (tayttamispaiva53.compareTo(kuluva) < 0) alku = kuluva;
        if (tayttamispaiva63.compareTo(Date.valueOf("2025-01-01")) < 0) loppu = tayttamispaiva63;
        
        Period aikavali = Period.between(alku.toLocalDate(), loppu.toLocalDate());
        
        return Math.max(0, 12 * aikavali.getYears() + aikavali.getMonths() + 1);
    }
    
    public Date annaIanTayttamispaiva (int syntymavuosi, int syntymakuukausi, int ikavuodet, int ikakuukaudet) {
        // palauttaa iän täyttämispäivän, mutta vähintään kuluvan päivän
        int alkamisVuosi = syntymavuosi + ikavuodet;
        int alkamisKuukausi = syntymakuukausi + ikakuukaudet + 1;
        
        if (alkamisKuukausi > 12) {
            alkamisVuosi++;
            alkamisKuukausi /= 12;
        }
        
        Date kuluva = Date.valueOf(LocalDate.now());
        Date tayttamispaiva = Date.valueOf(alkamisVuosi + "-" + alkamisKuukausi + "-01");
        
        if (tayttamispaiva.compareTo(kuluva) < 0) return kuluva;
        return tayttamispaiva;
    }
    
}
