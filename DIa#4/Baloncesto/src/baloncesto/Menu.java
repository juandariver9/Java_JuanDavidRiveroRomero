package baloncesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Partido> partidos;
    private Scanner scanner;

    public Menu() {
        partidos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("Menu:");
            System.out.println("1. Registrar Partido de Liga");
            System.out.println("2. Registrar Partido PlayOff");
            System.out.println("3. Finalizar Partido");
            System.out.println("4. Mostrar Información del Partido");
            System.out.println("5. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (opcion) {
                case 1:
                    registrarPartidoLiga();
                    break;
                case 2:
                    registrarPartidoPlayOff();
                    break;
                case 3:
                    finalizarPartido();
                    break;
                case 4:
                    mostrarInformacionPartido();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void registrarPartidoLiga() {
        System.out.print("Equipo Local: ");
        String equipoLocal = scanner.nextLine();
        System.out.print("Equipo Visitante: ");
        String equipoVisitante = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr);
        System.out.print("Jornada: ");
        int jornada = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Partido partido = new PartidoLiga(equipoLocal, equipoVisitante, fecha, jornada);
        partidos.add(partido);
        System.out.println("Partido de Liga registrado.");
    }

    private void registrarPartidoPlayOff() {
        System.out.print("Equipo Local: ");
        String equipoLocal = scanner.nextLine();
        System.out.print("Equipo Visitante: ");
        String equipoVisitante = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr);
        System.out.print("Ronda: ");
        String ronda = scanner.nextLine();

        Partido partido = new PartidoPlayOff(equipoLocal, equipoVisitante, fecha, ronda);
        partidos.add(partido);
        System.out.println("Partido PlayOff registrado.");
    }

    private void finalizarPartido() {
        System.out.print("Índice del Partido a finalizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        if (indice >= 0 && indice < partidos.size()) {
            Partido partido = partidos.get(indice);
            partido.finalizarPartido();
            System.out.println("Partido finalizado.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private void mostrarInformacionPartido() {
        System.out.print("Índice del Partido a mostrar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        if (indice >= 0 && indice < partidos.size()) {
            Partido partido = partidos.get(indice);
            System.out.println(partido.obtenerInformacion());
        } else {
            System.out.println("Índice no válido.");
        }
    }
}
                     