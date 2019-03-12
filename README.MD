<h1>ELÄKELASKURI</h1>

Tämä on henkilökohtainen harjoitusprojekti, jossa toteutan laskurin TyEL:n mukaisen vanhuuseläkkeen arvion laskentaan. Toistaiseksi laskurissa on kaksi toiminnallisuutta:
*(1)* laske arvio vanhuuseläkkeesi määrästä
*(2)* tarkasta ikäluokkakohtainen (arvioitu) eläkeikäsi.

<h2>Toteutuksesta</h2>

Sovellus on toteutettu Maven projektina Java-kielellä. Sovellus käyttää H2 tietokantaa Spring Boot sovelluskehyksen ja JDBC-rajapinnan kautta. Sovelluksessa on tekstikäyttöliittymä.

<h2>Ohjelman käynnistäminen</h2>

Ohjelman käynnistämiseen tarkoitettu .jar tiedosto löytyy kansiosta /ElakelaskuriSovellus/ElakelaskuriSovellus.jar ja se käynnistetään suorittamalla komento:

*java -jar ElakelaskuriSovellus.jar*

**Huom:** Sovellus muodostaa tietokannan samaan kansioon, missä ohjelma suoritetaan - ja ohjelma olettaa löytävänsä myös tietokannan luomiseen tarkoitetut scriptit kyseisestä kansiosta. Ennen ohjelman suoritusta, kopioi tiedostot /ElakelaskuriSovellus/luo_tietokanta.sql ja /ElakelaskuriSovellus/luo_data.sql samaan kansioon ohjelman kanssa.

**Tarkempi kuvaus ohjelman toiminnasta:**

[Laskentaohjelman_maarittely.pdf](https://github.com/MiguelSombrero/elakelaskuri/blob/master/Maarittelyt/Laskentaohjelman_maarittely.pdf)

