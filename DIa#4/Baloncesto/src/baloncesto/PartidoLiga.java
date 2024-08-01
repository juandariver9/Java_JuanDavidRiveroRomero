package baloncesto;

import java.time.LocalDate;

public class PartidoLiga extends Partido {
    private final int jornada;

    public PartidoLiga(String equipoLocal, String equipoVisitante, LocalDate fecha, int jornada) {
        super(equipoLocal, equipoVisitante, fecha);
        this.jornada = jornada;
    }

    @Override
    public String obtenerInformacion() {
        return "Partido de Liga - Jornada " + jornada + ": " + obtenerResultado();
    }
}
