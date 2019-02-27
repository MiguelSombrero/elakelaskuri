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
    ylaraja INTEGER,
    vahvistettu BOOLEAN
);