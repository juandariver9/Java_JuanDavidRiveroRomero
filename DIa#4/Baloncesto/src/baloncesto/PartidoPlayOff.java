package baloncesto;

import java.time.LocalDate;

public class PartidoPlayOff extends Partido {
    private String ronda;

    public PartidoPlayOff(String equipoLocal, String equipoVisitante, LocalDate fecha, String ronda) {
        super(equipoLocal, equipoVisitante, fecha);
        this.ronda = ronda;
    }

    @Override
    public String obtenerInformacion() {
        return "Partido PlayOff - Ronda " + ronda + ": " + obtenerResultado();
    }

    @Override
    public void finalizarPartido() {
        if (cestasLocal != cestasVisitante) {
            super.finalizarPartido();
        } else {
            System.out.println("El partido no puede finalizar en empate en PlayOffs.");
        }
    }
}

