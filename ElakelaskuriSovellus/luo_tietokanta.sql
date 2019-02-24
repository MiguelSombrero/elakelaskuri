DROP ALL OBJECTS;

CREATE TABLE Elinaikakerroin (
    syntymavuosi INTEGER PRIMARY KEY,
    elinaikakerroin NUMERIC(6,5),
    vahvistettu BOOLEAN
);

CREATE TABLE Elakeika (
    syntymavuosi INTEGER PRIMARY KEY,
    vuodet INTEGER,
    kuukaudet INTEGER,
    vahvistettu BOOLEAN
);

CREATE TABLE Laskelma (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    elakelaji VARCHAR(20),
    maara INTEGER
);

