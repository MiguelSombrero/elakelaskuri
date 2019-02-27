
package elakelaskurisovellus;

import elakelaskurisovellus.dao.*;
import elakelaskurisovellus.domain.*;
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
        
        Date alkuperainenAlkamispaiva = annaIanTayttamispaivaTaiKuluva(
                henkilo.getSyntymavuosi(),
                henkilo.getSyntymakuukausi(),
                ika.getVuodet(),
                ika.getKuukaudet(),
                true);
        
        Date elakeianTayttamispaiva = annaIanTayttamispaivaTaiKuluva(
                henkilo.getSyntymavuosi(),
                henkilo.getSyntymakuukausi(),
                ika.getVuodet(),
                ika.getKuukaudet(),
                false);
        
        Date ylaianTayttamispaiva = annaIanTayttamispaivaTaiKuluva(
                henkilo.getSyntymavuosi(),
                henkilo.getSyntymakuukausi(),
                ika.getYlaraja(),
                0,
                false);
        
        Date tayttamispaiva53 = annaIanTayttamispaivaTaiKuluva(
                henkilo.getSyntymavuosi(),
                henkilo.getSyntymakuukausi(),
                53,
                0,
                true);
        
        Date tayttamispaiva63 = annaIanTayttamispaivaTaiKuluva(
                henkilo.getSyntymavuosi(),
                henkilo.getSyntymakuukausi(),
                63,
                0,
                true);
        
        List<Laskelma> laskelmat = annaLaskelmat(3, henkilo, ika, alkuperainenAlkamispaiva, elakeianTayttamispaiva, ylaianTayttamispaiva);
        
        System.out.println(ika.toString());
        
        for (Laskelma laskelma : laskelmat) {
            System.out.println("\n" + laskeArvio(laskelma, henkilo, ika, eak, tayttamispaiva53, tayttamispaiva63, ylaianTayttamispaiva).toString());
        }
    }
    
    public List<Laskelma> annaLaskelmat (int montako, Henkilo henkilo, Elakeika ika, Date alkuperainenAlkamispaiva, Date elakeianTayttamispaiva, Date ylaianTayttamispaiva) {
        List<Laskelma> laskelmat = new ArrayList<>();
        
        for (int i = 0; i < montako; i++) {
            Date alku = Date.valueOf(alkuperainenAlkamispaiva.toLocalDate().plusYears(i));
            
            int lykkayskuukaudet = annaKuukaudet(
                    elakeianTayttamispaiva,
                    alku,
                    Date.valueOf("1900-01-01"),
                    ylaianTayttamispaiva);
            
            int ikaVuodet = ika.getVuodet() + lykkayskuukaudet/12;
            int ikaKuukaudet = ika.getKuukaudet() + lykkayskuukaudet%12;
            
            laskelmat.add(new Laskelma("Vanhuuseläke", alku, 0, lykkayskuukaudet, ikaVuodet, ikaKuukaudet));
        }
            
        return laskelmat;
    }
    
    public Laskelma laskeArvio (Laskelma laskelma, Henkilo henkilo, Elakeika ika, Elinaikakerroin eak, Date tayttamispaiva53, Date tayttamispaiva63, Date ylaianTayttamispaiva) {
        int kuukausiaAlkamiseen = annaKuukaudet(
                Date.valueOf(LocalDate.now()),
                laskelma.getAlkamispaiva(),
                Date.valueOf("1900-01-01"),
                ylaianTayttamispaiva);
        
        int kuukaudet17 = annaKuukaudet(
                tayttamispaiva53,
                tayttamispaiva63,
                Date.valueOf(LocalDate.now()),
                Date.valueOf("2025-12-31"));
        
        // lasketaan arvioidun eläkkeen määrä 1,7 % vuodessa
        double arvioituElake17 = henkilo.getPalkka() * 0.017 * kuukaudet17 / 12;
        
        // lasketaan arvioidun eläkkeen määrä 1,5 % vuodessa
        double arvioituElake15 = henkilo.getPalkka() * 0.015 * (kuukausiaAlkamiseen-kuukaudet17) / 12;
        
        // summataan arvioitu ja karttunut eläke, karttuneesta eläkkeestä poistetaan eak vaikutus
        double elakeYhteensa = arvioituElake15 + arvioituElake17 + henkilo.getKarttunutEläke() / eak.getElinaikakerroin();
        
        // lasketaan mahdollinen lykkäyskorotus 0,4% kuukaudessa koko eläkkeeseen
        elakeYhteensa += laskelma.getLykkayskuukaudet() * 0.004 * elakeYhteensa;
        
        // lasketaan elinaikakerroinmuunto koko eläkkeeseen
        elakeYhteensa *= eak.getElinaikakerroin();
        
        laskelma.setMaara((int) elakeYhteensa);
        
        return laskelma;
    }
    
    public int annaKuukaudet (Date mista, Date mihin, Date min, Date max) {
        Date alku = min;
        Date loppu = max;
        
        if (alku.before(mista)) alku = mista;
        if (loppu.after(mihin)) loppu = mihin;
        
        Period vali = Period.between(alku.toLocalDate(), loppu.toLocalDate());
        
        return (12 * vali.getYears() + vali.getMonths());
    }
    
    public Date annaIanTayttamispaivaTaiKuluva (int syntymavuosi, int syntymakuukausi, int ikavuodet, int ikakuukaudet, boolean vahintaan) {
        // palauttaa iän täyttämispäivää seuraavan kuun 1. päivän, mutta vähintään kuluvan päivän, jos niin ohjataan
        int alkamisVuosi = syntymavuosi + ikavuodet;
        int alkamisKuukausi = syntymakuukausi + ikakuukaudet + 1;
        
        if (alkamisKuukausi > 12) {
            alkamisVuosi++;
            alkamisKuukausi %= 12;
        }
        
        int kuluvaVuosi = LocalDate.now().getYear();
        int kuluvaKuukausi = LocalDate.now().getMonthValue() + 1;
        
        if (kuluvaKuukausi > 12) {
            kuluvaVuosi++;
            kuluvaKuukausi %= 12;
        }
        
        Date kuluva = Date.valueOf(kuluvaVuosi + "-" + kuluvaKuukausi + "-01");
        Date tayttamispaiva = Date.valueOf(alkamisVuosi + "-" + alkamisKuukausi + "-01");
        
        if (vahintaan && tayttamispaiva.before(kuluva)) return kuluva;
        return tayttamispaiva;
    }
    
}
