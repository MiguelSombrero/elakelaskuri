
package elakelaskurisovellus.domain;

public class Elakeika {
    
    private Integer syntymavuosi;
    private Integer vuodet;
    private Integer kuukaudet;
    private Integer ylaraja;
    private boolean vahvistettu;
    
    public Elakeika () {
    }
    
    public Elakeika (Integer syntymavuosi, Integer vuodet, Integer kuukaudet, Integer ylaraja, boolean vahvistettu) {
        this.syntymavuosi = syntymavuosi;
        this.vuodet = vuodet;
        this.kuukaudet = kuukaudet;
        this.ylaraja = ylaraja;
        this.vahvistettu = vahvistettu;
    }
    
    public Integer getSyntymavuosi () {
        return this.syntymavuosi;
    }
    
    public Integer getVuodet () {
        return this.vuodet;
    }
    
    public Integer getKuukaudet () {
        return this.kuukaudet;
    }
    
    public Integer getYlaraja () {
        return this.ylaraja;
    }
    
    public boolean getVahvistettu () {
        return this.vahvistettu;
    }
    
    public void setSyntymavuosi (Integer svuosi) {
        this.syntymavuosi = svuosi;
    }
    
    public void setVuodet (Integer vuosi) {
        this.vuodet = vuosi;
    }
    
    public void setKuukaudet (Integer kuukaudet) {
        this.kuukaudet = kuukaudet;
    }
    
    public void setYlaraja (Integer ylaraja) {
        this.ylaraja = ylaraja;
    }
    
    public void setVahvistettu (boolean vahvistettu) {
        this.vahvistettu = vahvistettu;
    }
    
    public String toString () {
        if (this.vahvistettu) return "Eläkeikäsi on " + this.vuodet + " vuotta ja " + this.kuukaudet + " kuukautta";
        else return "Arvioitu eläkeikäsi on " + this.vuodet + " vuotta ja " + this.kuukaudet + " kuukautta";
    }
}
