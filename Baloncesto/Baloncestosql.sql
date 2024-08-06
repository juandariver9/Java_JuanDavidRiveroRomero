
use bh59dywtu2w7ahagvpdt;


CREATE TABLE Equipo (
    ID_Equipo INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Ciudad VARCHAR(100) NOT NULL,
    Fundado DATE
);

CREATE TABLE Partido (
    ID_Partido INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE NOT NULL,
    ID_Equipo_Local INT,
    Cestas_Equipo_Local INT NOT NULL,
    ID_Equipo_Visitante INT,
    Cestas_Equipo_Visitante INT NOT NULL,
    Estado ENUM('En Juego', 'Finalizado') NOT NULL,
    Ganador INT,
    FOREIGN KEY (ID_Equipo_Local) REFERENCES Equipo(ID_Equipo),
    FOREIGN KEY (ID_Equipo_Visitante) REFERENCES Equipo(ID_Equipo),
    FOREIGN KEY (Ganador) REFERENCES Equipo(ID_Equipo)  
);

CREATE TABLE PartidoLiga (
    ID_Partido INT PRIMARY KEY,
    Jornada INT NOT NULL,
    FOREIGN KEY (ID_Partido) REFERENCES Partido(ID_Partido)
);

CREATE TABLE PartidoPlayOff (
    ID_Partido INT PRIMARY KEY,
    Ronda ENUM('Octavos', 'Cuartos', 'Semifinal', 'Final') NOT NULL,
    FOREIGN KEY (ID_Partido) REFERENCES Partido(ID_Partido)
);

Select * From Equipo;
