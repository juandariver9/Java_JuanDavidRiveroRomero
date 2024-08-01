package baloncesto;

import java.time.LocalDate;

public abstract class Partido {
    protected String equipoLocal;
    protected String equipoVisitante;
    protected int cestasLocal;
    protected int cestasVisitante;
    protected boolean finalizado;
    protected LocalDate fecha;

    public Partido(String equipoLocal, String equipoVisitante, LocalDate fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
        this.cestasLocal = 0;
        this.cestasVisitante = 0;
        this.finalizado = false;
    }

    public void registrarPuntosLocal(int puntos) {
        if (!finalizado) {
            cestasLocal += puntos;
        }
    }

    public void registrarPuntosVisitante(int puntos) {
        if (!finalizado) {
            cestasVisitante += puntos;
        }
    }

    public String obtenerResultado() {
        return equipoLocal + " " + cestasLocal + " - " + equipoVisitante + " " + cestasVisitante;
    }

    public String obtenerGanador() {
        if (cestasLocal > cestasVisitante) {
            return equipoLocal;
        } else if (cestasLocal < cestasVisitante) {
            return equipoVisitante;
        } else {
            return "Empate";
        }
    }

    public void finalizarPartido() {
        this.finalizado = true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public abstract String obtenerInformacion();
}
