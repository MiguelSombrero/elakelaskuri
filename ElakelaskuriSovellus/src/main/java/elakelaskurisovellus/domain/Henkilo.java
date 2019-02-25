
package elakelaskurisovellus.domain;

public class Henkilo {
    
    private Integer id;
    private Integer syntymavuosi;
    private Integer syntymakuukausi;
    private Integer karttunutElake;
    private Integer palkka;
    
    public Henkilo (Integer id, Integer syntymavuosi, Integer syntymakuukausi, Integer karttunutElake, Integer palkka) {
        this.id = id;
        this.karttunutElake = karttunutElake;
        this.palkka = palkka;
        this.syntymakuukausi = syntymakuukausi;
        this.syntymavuosi = syntymavuosi;
    }
    
    public Henkilo (Integer syntymavuosi, Integer syntymakuukausi, Integer karttunutElake, Integer palkka) {
        this(null, syntymavuosi, syntymakuukausi, karttunutElake, palkka);
    }
    
    public void setId (Integer id) {
        this.id = id;
    }
    
    public void setSyntymakuukausi (Integer kuukausi) {
        this.syntymakuukausi = kuukausi;
    }
    
    public void setSyntymavuosi (Integer vuosi) {
        this.syntymavuosi = vuosi;
    }
    
    public void setKarttunutEläke (Integer karttunut) {
        this.karttunutElake = karttunut;
    }
    
    public void setPalkka (Integer palkka) {
        this.palkka = palkka;
    }
    
    public Integer getId () {
        return this.id;
    }
    
    public Integer getSyntymakuukausi () {
        return this.syntymakuukausi;
    }
    
    public Integer getSyntymavuosi () {
        return this.syntymavuosi;
    }
    
    public Integer getKarttunutEläke () {
        return this.karttunutElake;
    }
    
    public Integer getPalkka () {
        return this.palkka;
    }
    
    public String toString () {
        return "";
    }
    
}
