package hospitaljava1;

import java.sql.*;
import java.util.Scanner;

public class HospitalJava1 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Conexion.init(); // Iniciar la conexión a la base de datos
        if (Conexion.getConnection() == null) {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
            return;
        }

        int option;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Gestionar Hospital");
            System.out.println("2. Gestionar Departamento");
            System.out.println("3. Gestionar Personal");
            System.out.println("4. Gestionar Doctor");
            System.out.println("5. Gestionar Equipo");
            System.out.println("6. Gestionar Pabellon");
            System.out.println("7. Gestionar Paciente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> manageHospital();
                case 2 -> manageDepartamento();
                case 3 -> managePersonal();
                case 4 -> manageDoctor();
                case 5 -> manageEquipo();
                case 6 -> managePabellon();
                case 7 -> managePaciente();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void manageHospital() {
        int option;
        do {
            System.out.println("\nGestión de Hospitales:");
            System.out.println("1. Añadir Hospital");
            System.out.println("2. Ver Hospitales");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> addHospital();
                case 2 -> displayHospitals();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void addHospital() {
        System.out.print("Nombre del Hospital: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección del Hospital: ");
        String direccion = scanner.nextLine();

        String insertHospital = "INSERT INTO Hospital (nombre, direccion) VALUES (?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertHospital)) {
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.executeUpdate();
            System.out.println("Hospital añadido exitosamente.");
        } catch (SQLException e) {
        }
    }

    private static void displayHospitals() {
        String query = "SELECT id, nombre FROM Hospital";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println(id + ": " + nombre);
            }
        } catch (SQLException e) {
        }
    }

    private static void manageDepartamento() {
        int option;
        do {
            System.out.println("\nGestión de Departamentos:");
            System.out.println("1. Añadir Departamento");
            System.out.println("2. Ver Departamentos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> addDepartamento();
                case 2 -> displayDepartamentos();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void addDepartamento() {
        System.out.print("Nombre del Departamento: ");
        String nombre = scanner.nextLine();
        System.out.print("ID del Hospital: ");
        int hospitalId = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        String insertDepartamento = "INSERT INTO Departamento (nombre, hospital_id) VALUES (?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertDepartamento)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, hospitalId);
            stmt.executeUpdate();
            System.out.println("Departamento añadido exitosamente.");
        } catch (SQLException e) {
        }
    }

    private static void displayDepartamentos() {
        String query = "SELECT id, nombre FROM Departamento";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println(id + ": " + nombre);
            }
        } catch (SQLException e) {
        }
    }

    private static void managePersonal() {
        int option;
        do {
            System.out.println("\nGestión de Personal:");
            System.out.println("1. Añadir Personal");
            System.out.println("2. Ver Personal");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> addPersonal();
                case 2 -> displayPersonal();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void addPersonal() {
        System.out.print("ID de la Persona: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea
        System.out.print("Fecha de vinculación (YYYY-MM-DD): ");
        String fechaVinculacion = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();  // Consumir nueva línea
        System.out.print("ID del Departamento: ");
        int departamentoId = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea
        System.out.print("Tipo de Personal (administrativo, tecnico, operaciones): ");
        String tipoPersonal = scanner.nextLine();
        String tipoOperacion = null, tipoTecnico = null;
        if (tipoPersonal.equals("operaciones")) {
            System.out.print("Tipo de Operación (doctor, enfermera): ");
            tipoOperacion = scanner.nextLine();
        } else if (tipoPersonal.equals("tecnico")) {
            System.out.print("Tipo de Técnico (tecnico, tecnologia): ");
            tipoTecnico = scanner.nextLine();
        }

        String insertPersonal = "INSERT INTO Personal (id, fecha_vinculacion, salario, departamento_id, tipo_personal, tipo_operacion, tipo_tecnico) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertPersonal)) {
            stmt.setInt(1, id);
            stmt.setString(2, fechaVinculacion);
            stmt.setDouble(3, salario);
            stmt.setInt(4, departamentoId);
            stmt.setString(5, tipoPersonal);
            stmt.setString(6, tipoOperacion);
            stmt.setString(7, tipoTecnico);
            stmt.executeUpdate();
            System.out.println("Personal añadido exitosamente.");
        } catch (SQLException e) {
        }
    }

    private static void displayPersonal() {
        String query = "SELECT id, fecha_vinculacion, salario, departamento_id, tipo_personal FROM Personal";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Date fechaVinculacion = rs.getDate("fecha_vinculacion");
                double salario = rs.getDouble("salario");
                int departamentoId = rs.getInt("departamento_id");
                String tipoPersonal = rs.getString("tipo_personal");
                System.out.println(id + ": " + fechaVinculacion + ", " + salario + ", " + departamentoId + ", " + tipoPersonal);
            }
        } catch (SQLException e) {
        }
    }

    private static void manageDoctor() {
        int option;
        do {
            System.out.println("\nGestión de Doctores:");
            System.out.println("1. Añadir Doctor");
            System.out.println("2. Ver Doctores");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> addDoctor();
                case 2 -> displayDoctors();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void addDoctor() {
        System.out.print("ID de Personal: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea
        System.out.print("¿Es líder? (true/false): ");
        boolean esLider = scanner.nextBoolean();

        String insertDoctor = "INSERT INTO Doctor (id, es_lider) VALUES (?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertDoctor)) {
            stmt.setInt(1, id);
            stmt.setBoolean(2, esLider);
            stmt.executeUpdate();
            System.out.println("Doctor añadido exitosamente.");
        } catch (SQLException e) {
        }
    }

    private static void displayDoctors() {
        String query = "SELECT d.id, p.nombre_pila, p.apellidos, d.es_lider " +
                       "FROM Doctor d JOIN Personal p ON d.id = p.id";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombrePila = rs.getString("nombre_pila");
                String apellidos = rs.getString("apellidos");
                boolean esLider = rs.getBoolean("es_lider");
                System.out.println(id + ": " + nombrePila + " " + apellidos + ", Líder: " + esLider);
            }
        } catch (SQLException e) {
        }
    }

    private static void manageEquipo() {
        int option;
        do {
            System.out.println("\nGestión de Equipos:");
            System.out.println("1. Añadir Equipo");
            System.out.println("2. Ver Equipos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> addEquipo();
                case 2 -> displayEquipos();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void addEquipo() {
        System.out.print("Nombre del Equipo: ");
        String nombre = scanner.nextLine();
        System.out.print("ID del Líder: ");
        int liderId = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        String insertEquipo = "INSERT INTO Equipo (nombre, lider_id) VALUES (?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertEquipo)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, liderId);
            stmt.executeUpdate();
            System.out.println("Equipo añadido exitosamente.");
        } catch (SQLException e) {
        }
    }

    private static void displayEquipos() {
        String query = "SELECT id, nombre FROM Equipo";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println(id + ": " + nombre);
            }
        } catch (SQLException e) {
        }
    }

    private static void managePabellon() {
        int option;
        do {
            System.out.println("\nGestión de Pabellones:");
            System.out.println("1. Añadir Pabellón");
            System.out.println("2. Ver Pabellones");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> addPabellon();
                case 2 -> displayPabellones();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void addPabellon() {
        System.out.print("Nombre del Pabellón: ");
        String nombre = scanner.nextLine();
        System.out.print("Capacidad del Pabellón: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        String insertPabellon = "INSERT INTO Pabellon (nombre, capacidad) VALUES (?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertPabellon)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, capacidad);
            stmt.executeUpdate();
            System.out.println("Pabellón añadido exitosamente.");
        } catch (SQLException e) {
        }
    }

    private static void displayPabellones() {
        String query = "SELECT id, nombre, capacidad FROM Pabellon";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int capacidad = rs.getInt("capacidad");
                System.out.println(id + ": " + nombre + ", Capacidad: " + capacidad);
            }
        } catch (SQLException e) {
        }
    }

    private static void managePaciente() {
        int option;
        do {
            System.out.println("\nGestión de Pacientes:");
            System.out.println("1. Añadir Paciente");
            System.out.println("2. Ver Pacientes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (option) {
                case 1 -> addPaciente();
                case 2 -> displayPacientes();
                case 0 -> {
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void addPaciente() {
        System.out.print("ID de la Persona: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea
        System.out.print("Fecha de Ingreso (YYYY-MM-DD): ");
        String fechaIngreso = scanner.nextLine();
        System.out.print("ID del Pabellón: ");
        int pabellonId = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        String insertPaciente = "INSERT INTO Paciente (id, fecha_ingreso, pabellon_id) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertPaciente)) {
            stmt.setInt(1, id);
            stmt.setString(2, fechaIngreso);
            stmt.setInt(3, pabellonId);
            stmt.executeUpdate();
            System.out.println("Paciente añadido exitosamente.");
        } catch (SQLException e) {
        }
    }

    private static void displayPacientes() {
        String query = "SELECT p.id, p.fecha_ingreso, pab.nombre " +
                       "FROM Paciente p JOIN Pabellon pab ON p.pabellon_id = pab.id";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Date fechaIngreso = rs.getDate("fecha_ingreso");
                String nombrePabellon = rs.getString("nombre");
                System.out.println(id + ": " + fechaIngreso + ", Pabellón: " + nombrePabellon);
            }
        } catch (SQLException e) {
        }
    }
}