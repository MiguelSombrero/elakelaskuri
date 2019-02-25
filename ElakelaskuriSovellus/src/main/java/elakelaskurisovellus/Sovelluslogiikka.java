
package elakelaskurisovellus;

import elakelaskurisovellus.dao.*;
import elakelaskurisovellus.domain.Elakeika;
import elakelaskurisovellus.domain.Elinaikakerroin;
import elakelaskurisovellus.domain.Henkilo;
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
    
    public void tarkistaElakeika (int vuosi) throws SQLException {
        System.out.println(elakeika.read(vuosi).toString());
    }
    
    public void laskeElakearviot (Henkilo henkilo) throws SQLException {
        Elakeika ika = elakeika.read(henkilo.getSyntymavuosi());
        Elinaikakerroin eak = elinaikakerroin.read(henkilo.getSyntymavuosi());
        List<Laskelma> laskelmat = annaLaskelmat(3, henkilo, ika);
        
        System.out.println(ika.toString());
        
        for (Laskelma laskelma : laskelmat) {
            System.out.println("\n" + laskeArvio(laskelma, henkilo, ika, eak).toString());
        }
    }
    
    public List<Laskelma> annaLaskelmat (int montako, Henkilo henkilo, Elakeika ika) {
        List<Laskelma> laskelmat = new ArrayList<>();
        Date alkuperainenAlkamispaiva = annaIanTayttamispaivaTaiKuluva(henkilo.getSyntymavuosi(), henkilo.getSyntymakuukausi(), ika.getVuodet(), ika.getKuukaudet(), true);
        Date elakeIanTayttamispaiva = annaIanTayttamispaivaTaiKuluva(henkilo.getSyntymavuosi(), henkilo.getSyntymakuukausi(), ika.getVuodet(), ika.getKuukaudet(), false);
        
        for (int i = 0; i < montako; i++) {
            Date alku = Date.valueOf(alkuperainenAlkamispaiva.toLocalDate().plusYears(i));
            int lykkayskuukaudet = annaKuukaudet(elakeIanTayttamispaiva, alku);
            int ikaVuodet = ika.getVuodet() + lykkayskuukaudet/12;
            int ikaKuukaudet = ika.getKuukaudet() + lykkayskuukaudet%12;
            
            laskelmat.add(new Laskelma("Vanhuuseläke", alku, 0, lykkayskuukaudet, ikaVuodet, ikaKuukaudet));
        }
            
        return laskelmat;
    }
    
    public Laskelma laskeArvio (Laskelma laskelma, Henkilo henkilo, Elakeika ika, Elinaikakerroin eak) {
        int kuukausiaAlkamiseen = annaKuukaudet(Date.valueOf(LocalDate.now()), laskelma.getAlkamispaiva());
        int kk17 = annaKuukaudet17karttumaan(henkilo.getSyntymavuosi(), henkilo.getSyntymakuukausi());
        
        // lasketaan arvioidun eläkkeen määrä 1,7 % vuodessa, ansion korotus 2% vuodessa
        double arvioituElake17 = henkilo.getPalkka() * 0.017 * kk17 / 12;
        
        // lasketaan arvioidun eläkkeen määrä 1,5 % vuodessa, ansion korotus 2% vuodessa
        double arvioituElake15 = henkilo.getPalkka() * 0.015 * (kuukausiaAlkamiseen-kk17) / 12;
        
        // summataan arvioitu ja karttunut eläke, karttuneesta eläkkeestä poistetaan eak vaikutus
        double elakeYhteensa = arvioituElake15 + arvioituElake17 + henkilo.getKarttunutEläke() / eak.getElinaikakerroin();
        
        // lasketaan mahdollinen lykkäyskorotus 0,4% kuukaudessa koko eläkkeeseen
        elakeYhteensa += laskelma.getLykkayskuukaudet() * 0.004 * elakeYhteensa;
        
        // lasketaan elinaikakerroinmuunto koko eläkkeeseen
        elakeYhteensa *= eak.getElinaikakerroin();
        
        laskelma.setMaara((int) elakeYhteensa);
        
        return laskelma;
    }
    
    public int annaKuukaudet (Date mista, Date mihin) {
        Period vali = Period.between(mista.toLocalDate(), mihin.toLocalDate());
        return (12 * vali.getYears() + vali.getMonths());
    }
    
    public int annaKuukaudet17karttumaan(int syntymavuosi, int syntymakuukausi) {
        Date tayttamispaiva53 = annaIanTayttamispaivaTaiKuluva(syntymavuosi, syntymakuukausi, 53, 0, true);
        Date tayttamispaiva63 = annaIanTayttamispaivaTaiKuluva(syntymavuosi, syntymakuukausi, 63, 0, true);
        Date kuluva = Date.valueOf(LocalDate.now());
        Date alku = tayttamispaiva53;
        Date loppu = Date.valueOf("2025-01-01");
        
        if (tayttamispaiva53.before(kuluva)) alku = kuluva;
        if (tayttamispaiva63.before(Date.valueOf("2025-01-01"))) loppu = tayttamispaiva63;
        
        Period aikavali = Period.between(alku.toLocalDate(), loppu.toLocalDate());
        
        return Math.max(0, 12 * aikavali.getYears() + aikavali.getMonths() + 1);
    }
    
    public Date annaIanTayttamispaivaTaiKuluva (int syntymavuosi, int syntymakuukausi, int ikavuodet, int ikakuukaudet, boolean vahintaan) {
        // palauttaa iän täyttämispäivää seuraavan kuun 1. päivän, mutta vähintään kuluvan päivän, jos niin ohjataan
        int alkamisVuosi = syntymavuosi + ikavuodet;
        int alkamisKuukausi = syntymakuukausi + ikakuukaudet + 1;
        
        if (alkamisKuukausi > 12) {
            alkamisVuosi++;
            alkamisKuukausi %= 12;
        }
        
        // ei toimi joulukuussa, muuta
        Date kuluva = Date.valueOf(LocalDate.now().getYear() + "-" + (LocalDate.now().getMonthValue()+1) + "-01");
        Date tayttamispaiva = Date.valueOf(alkamisVuosi + "-" + alkamisKuukausi + "-01");
        
        if (vahintaan && tayttamispaiva.before(kuluva)) return kuluva;
        return tayttamispaiva;
    }
    
}
