
Tämä on henkilökohtainen harjoitusprojekti, jossa toteutan yksinkertaisen laskurin TyEL:n mukaisen vanhuuseläkkeen arvion laskentaan. Laskurilla voi (1) laskea arvion kuukausieläkkeen määrästä ja (2) tarkastaa ikäluokkakohtaisen (arvioidun) eläkeiän. En kuvaa tässä projektissa tarkemmin eläkkeen laskentaa, siihen voi tutustua halutessaan TyEL eläkelaista:

https://finlex.fi/fi/laki/ajantasa/2006/20060395


TOTEUTUS:

Toteutan työn Maven Java-projektina. Ohjelma käyttää laskennassa H2 tietokantaa Spring Boot sovelluskehyksen ja JDBC-rajapinnan kautta. Ohjelman käynnistyessä, ohjelma tarkistaa löytyykö tietokanta ”elakelaskuri” ja tarvittavat taulut kansiosta /ElakelaskuriSovellus. Jos ei löydy, ohjelma luo ne ja datan. Tietokantaa käytetään ikäluokkakohtaisten eläkeikien ja elinaikakertoimien säilyttämiseen/lukemiseen. Tarvittaessa ohjelmaa voi laajentaa siten, että tietokantaan tallennetaan myös esimerkiksi henkilöt ja laskelmat.


HUOM: TARKEMPI KUVAUS LASKURIN TOIMINNASTA: /Maarittelyt/Laskentaohjelman_maarittely.pdf
