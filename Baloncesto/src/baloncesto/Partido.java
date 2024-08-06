package baloncesto;

import java.sql.Date;

public class Partido {
    private int idPartido;
    private Date fecha;
    private int idEquipoLocal;
    private int cestasEquipoLocal;
    private int idEquipoVisitante;
    private int cestasEquipoVisitante;
    private String estado;
    private int ganador;

    // Constructor
    public Partido(int idPartido, Date fecha, int idEquipoLocal, int idEquipoVisitante) {
        this.idPartido = idPartido;
        this.fecha = fecha;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.estado = "En Juego";
        this.cestasEquipoLocal = 0;
        this.cestasEquipoVisitante = 0;
        this.ganador = 0;
    }

    // Getters y Setters
    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(int idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public int getCestasEquipoLocal() {
        return cestasEquipoLocal;
    }

    public void setCestasEquipoLocal(int cestasEquipoLocal) {
        this.cestasEquipoLocal = cestasEquipoLocal;
    }

    public int getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(int idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public int getCestasEquipoVisitante() {
        return cestasEquipoVisitante;
    }

    public void setCestasEquipoVisitante(int cestasEquipoVisitante) {
        this.cestasEquipoVisitante = cestasEquipoVisitante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public void finalizarPartido() {
        this.estado = "Finalizado";
        if (cestasEquipoLocal > cestasEquipoVisitante) {
            this.ganador = idEquipoLocal;
        } else if (cestasEquipoVisitante > cestasEquipoLocal) {
            this.ganador = idEquipoVisitante;
        } else {
            this.ganador = 0; // Empate
        }
    }

    @Override
    public String toString() {
        return "Partido [ID: " + idPartido + ", Fecha: " + fecha + ", Local: " + idEquipoLocal + ", Visitante: " + idEquipoVisitante +
               ", Cestas Local: " + cestasEquipoLocal + ", Cestas Visitante: " + cestasEquipoVisitante +
               ", Estado: " + estado + ", Ganador: " + ganador + "]";
    }
}
