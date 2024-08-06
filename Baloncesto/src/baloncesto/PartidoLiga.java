package baloncesto;

import java.sql.Date;

public class PartidoLiga extends Partido {
    private int jornada;

    public PartidoLiga(int idPartido, Date fecha, int idEquipoLocal, int idEquipoVisitante, int jornada) {
        super(idPartido, fecha, idEquipoLocal, idEquipoVisitante);
        this.jornada = jornada;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return super.toString() + ", Jornada: " + jornada;
    }
}
