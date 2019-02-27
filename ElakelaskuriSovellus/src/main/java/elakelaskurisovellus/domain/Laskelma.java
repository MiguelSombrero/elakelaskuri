
package elakelaskurisovellus.domain;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Laskelma {
    
    private Integer id;
    private String elakelaji;
    private Date alkamispaiva;
    private Integer maara;
    private Integer lykkayskuukaudet;
    private Integer ikavuodet;
    private Integer ikakuukaudet;
    private Integer kuukaudet17karttumaan;
    private Integer kuukaudet15karttumaan;
    
    public Laskelma () {
        
    }
    
    public Laskelma (Integer id, String elakelaji, Date alkamispaiva, Integer maara, Integer lykkayskuukaudet, Integer ikavuodet, Integer ikakuukaudet, Integer kuukaudet17karttumaan, Integer kuukaudet15karttumaan) {
        this.id = id;
        this.elakelaji = elakelaji;
        this.alkamispaiva = alkamispaiva;
        this.maara = maara;
        this.lykkayskuukaudet = lykkayskuukaudet;
        this.ikavuodet = ikavuodet;
        this.ikakuukaudet = ikakuukaudet;
        this.kuukaudet15karttumaan = kuukaudet15karttumaan;
        this.kuukaudet17karttumaan = kuukaudet17karttumaan;
    }
    
    public Laskelma (String elakelaji, Date alkamispaiva, Integer maara, Integer lykkayskuukaudet, Integer ikavuodet, Integer ikakuukaudet, Integer kuukaudet17karttumaan, Integer kuukaudet15karttumaan) {
        this(null, elakelaji, alkamispaiva, maara, lykkayskuukaudet, ikavuodet, ikakuukaudet, kuukaudet17karttumaan, kuukaudet15karttumaan);
    }
    
    public void setElakelaji (String elakelaji) {
        this.elakelaji = elakelaji;
    }
    
    public void setAlkamispaiva (Date alkamispaiva) {
        this.alkamispaiva = alkamispaiva;
    }
    
    public void setMaara (Integer maara) {
        this.maara = maara;
    }
    
    public void setId (Integer id) {
        this.id = id;
    }
    
    public void setLykkayskuukaudet (Integer lykkayskuukaudet) {
        this.lykkayskuukaudet = lykkayskuukaudet;
    }
    
    public void setIkavuodet (Integer ikavuodet) {
        this.ikavuodet = ikavuodet;
    }
    
    public void setIkakuukaudet (Integer ikakuukaudet) {
        this.ikakuukaudet = ikakuukaudet;
    }
    
    public void setKuukaudet17karttumaan (Integer kuukaudet17) {
        this.kuukaudet17karttumaan = kuukaudet17;
    }
    
    public void setKuukaudet15karttumaan (Integer kuukaudet15) {
        this.kuukaudet15karttumaan = kuukaudet15;
    }
    
    public String getElakelaji () {
        return this.elakelaji;
    }
    
    public Date getAlkamispaiva () {
        return this.alkamispaiva;
    }
    
    public Integer getMaara () {
        return this.maara;
    }
    
    public Integer getId () {
        return this.id;
    }
    
    public Integer getLykkayskuukaudet () {
        return this.lykkayskuukaudet;
    }
    
    public Integer getIkavuodet () {
        return this.ikavuodet;
    }
    
    public Integer getIkakuukaudet () {
        return this.ikakuukaudet;
    }
    
    public Integer getKuukaudet17karttumaan () {
        return this.kuukaudet17karttumaan;
    }
    
    public Integer getKuukaudet15karttumaan () {
        return this.kuukaudet15karttumaan;
    }
    
    public String toString () {
        return "Eläkearvio " + this.ikavuodet + " vuoden ja " + this.ikakuukaudet +
                " kuukauden iässä, alkamispäivällä " + this.alkamispaiva.toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " on noin:\n" +
               this.maara + " euroa kuukaudessa.";
    }
}
