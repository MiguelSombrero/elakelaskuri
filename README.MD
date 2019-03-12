<h1>ELÄKELASKURI</h1>

Tämä on henkilökohtainen harjoitusprojekti, jossa toteutan laskurin TyEL:n mukaisen vanhuuseläkkeen arvion laskentaan. Laskurilla voi *(1)* laskea arvion vanhuuseläkkeen määrästä ja *(2)* tarkastaa ikäluokkakohtaisen (arvioidun) eläkeiän.

<h2>Toteutuksesta</h2>

Sovellus on toteutettu Maven projektina Java-kielellä. Sovellus käyttää H2 tietokantaa Spring Boot sovelluskehyksen ja JDBC-rajapinnan kautta. Sovelluksessa on tekstikäyttöliittymä.

<h2>Ohjelman käynnistäminen</h2>

Ohjelman käynnistämiseen tarkoitettu .jar tiedosto löytyy kansiosta /ElakelaskuriSovellus/ElakelaskuriSovellus.jar ja se käynnistetään suorittamalla komento:

<addr>java -jar Elakelaskuri.jar</addr>

**Huom:** Sovellus muodostaa tietokannan samaan kansioon, missä ohjelma suoritetaan - ja ohjelma olettaa löytävänsä myös tietokannan luomiseen tarkoitetut scriptit kyseisestä kansiosta. Ennen ohjelman suoritusta, kopioi tiedostot /ElakelaskuriSovellus/luo_tietokanta.sql ja /ElakelaskuriSovellus/luo_data.sql samaan kansioon ohjelman kanssa.

<h2>Tarkempi kuvaus ohjelman toiminnasta:</h2>

/Maarittelyt/Laskentaohjelman_maarittely.pdf

