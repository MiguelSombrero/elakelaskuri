
package elakelaskurisovellus.domain;

public class Elinaikakerroin {
    
    private Integer syntymavuosi;
    private double elinaikakerroin;
    private boolean vahvistettu;
    
    public Elinaikakerroin () {
    }
    
    public Elinaikakerroin (Integer syntymavuosi, double elinaikakerroin, boolean vahvistettu) {
        this.syntymavuosi = syntymavuosi;
        this.elinaikakerroin = elinaikakerroin;
        this.vahvistettu = vahvistettu;
    }
    
    public Integer getSyntymavuosi () {
        return this.syntymavuosi;
    }
    
    public double getElinaikakerroin () {
        return this.elinaikakerroin;
    }
    
    public boolean getVahvistettu () {
        return this.vahvistettu;
    }
    
    public void setSyntymavuosi (Integer svuosi) {
        this.syntymavuosi = svuosi;
    }
    
    public void setElinaikakerroin (double elinaikakerroin) {
        this.elinaikakerroin = elinaikakerroin;
    }
    
    public void setVahvistettu (boolean vahvistettu) {
        this.vahvistettu = vahvistettu;
    }
    
    public String toString () {
        if (this.vahvistettu) return "Eläkettäsi pienentävä elinaikakerroin on " + this.elinaikakerroin;
        else return "Eläkettäsi pienentävä arvioitu elinaikakerroin on " + this.elinaikakerroin;
    }
    
}
