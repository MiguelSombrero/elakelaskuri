
package elakelaskurisovellus.domain;

import java.sql.Date;

public class Laskelma {
    
    private Integer id;
    private String elakelaji;
    private Date alkamispaiva;
    private Integer maara;
    private Integer lykkayskuukaudet;
    private Integer ikavuodet;
    private Integer ikakuukaudet;
    
    public Laskelma () {
        
    }
    
    public Laskelma (Integer id, String elakelaji, Date alkamispaiva, Integer maara, Integer lykkayskuukaudet, Integer ikavuodet, Integer ikakuukaudet) {
        this.id = id;
        this.elakelaji = elakelaji;
        this.alkamispaiva = alkamispaiva;
        this.maara = maara;
        this.lykkayskuukaudet = lykkayskuukaudet;
        this.ikavuodet = ikavuodet;
        this.ikakuukaudet = ikakuukaudet;
    }
    
    public Laskelma (String elakelaji, Date alkamispaiva, Integer maara, Integer lykkayskuukaudet, Integer ikavuodet, Integer ikakuukaudet) {
        this(null, elakelaji, alkamispaiva, maara, lykkayskuukaudet, ikavuodet, ikakuukaudet);
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
    
    public String toString () {
        return "Eläkearvio " + this.ikavuodet + " vuoden ja " + this.ikakuukaudet + " kuukauden iässä, alkamispäivällä " + this.alkamispaiva + " on noin:\n" +
               this.maara + " euroa kuukaudessa";
    }
}
