
package elakelaskurisovellus.domain;

import java.sql.Date;

public class Laskelma {
    
    private Integer id;
    private String elakelaji;
    private Date alkamispaiva;
    private Integer maara;
    
    public Laskelma () {
        
    }
    
    public Laskelma (Integer id, String elakelaji, Date alkamispaiva, Integer maara) {
        this.id = id;
        this.elakelaji = elakelaji;
        this.alkamispaiva = alkamispaiva;
        this.maara = maara;
    }
    
    public Laskelma (String elakelaji, Date alkamispaiva, Integer maara) {
        this(null, elakelaji, alkamispaiva, maara);
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
    
    public String toString () {
        return "Eläkkeesi alkaa " + this.alkamispaiva + ".\n" +
                "Eläkkeen määrä on " + this.maara + " euroa kuukaudessa";
    }
}
