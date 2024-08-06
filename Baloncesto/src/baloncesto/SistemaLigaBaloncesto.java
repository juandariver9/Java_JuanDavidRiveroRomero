package baloncesto;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;

public class SistemaLigaBaloncesto {
    private final Connection connection;
    private final Scanner scanner;

    public SistemaLigaBaloncesto(Connection connection) {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }

    public void menuPrincipal() throws SQLException {
        int opcion = 0;
        while (opcion != 6) {
            System.out.println("Menú Principal:");
            System.out.println("1. Registrar Equipo");
            System.out.println("2. Ver Equipos");
            System.out.println("3. Registrar Partido");
            System.out.println("4. Ver Partidos");
            System.out.println("5. Modificar Cestas y Finalizar Partido");
            System.out.println("6. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarEquipo();
                case 2 -> verEquipos();
                case 3 -> registrarPartido();
                case 4 -> verPartidos();
                case 5 -> modificarCestasYFinalizar();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void registrarEquipo() throws SQLException {
        System.out.print("Nombre del equipo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ciudad del equipo: ");
        String ciudad = scanner.nextLine();
        System.out.print("Fecha de fundación (YYYY-MM-DD): ");
        String fundado = scanner.nextLine();

        String sql = "INSERT INTO Equipo (Nombre, Ciudad, Fundado) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, ciudad);
        statement.setString(3, fundado);
        statement.executeUpdate();

        System.out.println("Equipo registrado.");
    }

    private void verEquipos() throws SQLException {
        String sql = "SELECT * FROM Equipo";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ID_Equipo");
            String nombre = resultSet.getString("Nombre");
            String ciudad = resultSet.getString("Ciudad");
            String fundado = resultSet.getString("Fundado");
            System.out.println("ID: " + id + ", Nombre: " + nombre + ", Ciudad: " + ciudad + ", Fundado: " + fundado);
        }
    }

    private void registrarPartido() throws SQLException {
        System.out.print("Fecha del partido (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        Date fecha = Date.valueOf(fechaStr);
        System.out.print("ID del equipo local: ");
        int idLocal = scanner.nextInt();
        System.out.print("ID del equipo visitante: ");
        int idVisitante = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Es un partido de Liga o PlayOff? (L/P): ");
        String tipoPartido = scanner.nextLine();

        int idPartido = 0;
        String sql = "INSERT INTO Partido (Fecha, ID_Equipo_Local, ID_Equipo_Visitante, Estado) VALUES (?, ?, ?, 'En Juego')";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setDate(1, fecha);
        statement.setInt(2, idLocal);
        statement.setInt(3, idVisitante);
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            idPartido = rs.getInt(1);
        }

        if ("L".equalsIgnoreCase(tipoPartido)) {
            System.out.print("Número de jornada: ");
            int jornada = scanner.nextInt();
            scanner.nextLine();
            sql = "INSERT INTO PartidoLiga (ID_Partido, Jornada) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idPartido);
            statement.setInt(2, jornada);
            statement.executeUpdate();
        } else if ("P".equalsIgnoreCase(tipoPartido)) {
            System.out.print("Ronda (Octavos, Cuartos, Semifinal, Final): ");
            String ronda = scanner.nextLine();
            sql = "INSERT INTO PartidoPlayOff (ID_Partido, Ronda) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idPartido);
            statement.setString(2, ronda);
            statement.executeUpdate();
        }

        System.out.println("Partido registrado.");
    }

    private void verPartidos() throws SQLException {
        String sql = "SELECT * FROM Partido";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ID_Partido");
            Date fecha = resultSet.getDate("Fecha");
            int idLocal = resultSet.getInt("ID_Equipo_Local");
            int cestasLocal = resultSet.getInt("Cestas_Equipo_Local");
            int idVisitante = resultSet.getInt("ID_Equipo_Visitante");
            int cestasVisitante = resultSet.getInt("Cestas_Equipo_Visitante");
            String estado = resultSet.getString("Estado");
            int ganador = resultSet.getInt("Ganador");
            System.out.println("ID: " + id + ", Fecha: " + fecha + ", Local: " + idLocal + ", Visitante: " + idVisitante +
                               ", Cestas Local: " + cestasLocal + ", Cestas Visitante: " + cestasVisitante +
                               ", Estado: " + estado + ", Ganador: " + ganador);
        }
    }

    private void modificarCestasYFinalizar() throws SQLException {
        System.out.print("ID del partido: ");
        int idPartido = scanner.nextInt();
        System.out.print("Cestas del equipo local: ");
        int cestasLocal = scanner.nextInt();
        System.out.print("Cestas del equipo visitante: ");
        int cestasVisitante = scanner.nextInt();
        scanner.nextLine();

        String sql = "UPDATE Partido SET Cestas_Equipo_Local = ?, Cestas_Equipo_Visitante = ? WHERE ID_Partido = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cestasLocal);
        statement.setInt(2, cestasVisitante);
        statement.setInt(3, idPartido);
        statement.executeUpdate();

        sql = "SELECT ID_Equipo_Local, ID_Equipo_Visitante FROM Partido WHERE ID_Partido = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, idPartido);
        ResultSet rs = statement.executeQuery();
        int idLocal = 0, idVisitante = 0;
        if (rs.next()) {
            idLocal = rs.getInt("ID_Equipo_Local");
            idVisitante = rs.getInt("ID_Equipo_Visitante");
        }

        int ganador = 0;
        if (cestasLocal > cestasVisitante) {
            ganador = idLocal;
        } else if (cestasVisitante > cestasLocal) {
            ganador = idVisitante;
        }

        sql = "UPDATE Partido SET Ganador = ?, Estado = 'Finalizado' WHERE ID_Partido = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, ganador);
        statement.setInt(2, idPartido);
        statement.executeUpdate();

        System.out.println("Partido finalizado. Ganador: " + ganador);
    }

    public static void main(String[] args) {
        Conection conection = new Conection();
        Connection connection = conection.getConnection();
        if (connection != null) {
            try {
                SistemaLigaBaloncesto sistema = new SistemaLigaBaloncesto(connection);
                sistema.menuPrincipal();
            } catch (SQLException e) {
                System.out.println("Error");
            } finally {
                conection.closeConnection();
            }
        }
    }

}
