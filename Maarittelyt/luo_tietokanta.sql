DROP ALL OBJECTS;

CREATE TABLE Elinaikakerroin (
    syntymavuosi INTEGER PRIMARY KEY,
    elinaikakerroin NUMERIC(6,5),
    vahvistettu BOOLEAN
)

CREATE TABLE Elakeika (
    syntymavuosi INTEGER PRIMARY KEY,
    vuodet INTEGER,
    kuukaudet INTEGER,
    vahvistettu BOOLEAN
)

CREATE TABLE Henkilo (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    syntymavuosi INTEGER,
    syntymakuukausi INTEGER,
    karttunutElake INTEGER,
    kuukausiansiot INTEGER
)

CREATE TABLE Laskelma (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    henkilo_id INTEGER,
    elakelaji VARCHAR(20),
    maara INTEGER,
    FOREIGN KEY (henkilo_id) REFERENCES Henkilo(id)
)

