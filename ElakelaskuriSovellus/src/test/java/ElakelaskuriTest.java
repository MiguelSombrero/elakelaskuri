
import elakelaskurisovellus.Sovelluslogiikka;
import java.sql.SQLException;
import org.junit.Test;


public class ElakelaskuriTest {
    
    
    @Test
    public void laskeElake1 () {
        // syntymäpäivä 15.4.1958, karttunut eläke 1200 ja palkka 4200
        Sovelluslogiikka laskuri = new Sovelluslogiikka();
        
        String tulostus = "Eläkeikäsi on 63 vuotta ja 3 kuukautta\n" + "\n" +
                       "Eläkearvio 63 vuoden ja 11 kuukauden iässä, alkamispäivällä 2019-03-01 on noin:\n" +
                       "1238 euroa kuukaudessa. Lykkäyskuukaudet: 8\n" + "\n" +
                       "Eläkearvio 64 vuoden ja 11 kuukauden iässä, alkamispäivällä 2020-03-01 on noin:\n" +
                       "1361 euroa kuukaudessa. Lykkäyskuukaudet: 20\n" + "\n" +
                       "Eläkearvio 65 vuoden ja 11 kuukauden iässä, alkamispäivällä 2021-03-01 on noin:\n" +
                       "1490 euroa kuukaudessa. Lykkäyskuukaudet: 32";
        
    }
}
