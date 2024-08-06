package baloncesto;

import java.sql.Date;

public class PartidoPlayOff extends Partido {
    private String ronda;

    public PartidoPlayOff(int idPartido, Date fecha, int idEquipoLocal, int idEquipoVisitante, String ronda) {
        super(idPartido, fecha, idEquipoLocal, idEquipoVisitante);
        this.ronda = ronda;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    @Override
    public String toString() {
        return super.toString() + ", Ronda: " + ronda;
    }
}
