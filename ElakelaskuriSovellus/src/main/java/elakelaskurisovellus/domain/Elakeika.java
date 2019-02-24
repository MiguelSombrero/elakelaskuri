
package elakelaskurisovellus.domain;

public class Elakeika {
    
    private Integer syntymavuosi;
    private Integer vuodet;
    private Integer kuukaudet;
    private boolean vahvistettu;
    
    public Elakeika () {
        
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
    
    public void setVahvistettu (boolean vahvistettu) {
        this.vahvistettu = vahvistettu;
    }
    
    public String toString () {
        if (this.vahvistettu) return "Eläkeikäsi on " + this.vuodet + " vuotta ja " + this.kuukaudet + " kuukautta";
        else return "Arvioitu eläkeikäsi on " + this.vuodet + " vuotta ja " + this.kuukaudet + " kuukautta";
    }
}
