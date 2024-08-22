package sdf;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SDF {

    private static List<Producto> productos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Factura> facturas = new ArrayList<>();

    public static void main(String[] args) {
        Conexion.init();
        Connection con = Conexion.getConnection();

        if (con != null) {
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("=== Sistema de Facturación ===");
                System.out.println("1. Agregar Producto");
                System.out.println("2. Agregar Cliente");
                System.out.println("3. Crear Factura");
                System.out.println("4. Ver Facturas");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        agregarProducto(sc);
                        break;
                    case 2:
                        agregarCliente(sc);
                        break;
                    case 3:
                        crearFactura(sc);
                        break;
                    case 4:
                        verFacturas();
                        break;
                    case 5:
                        System.out.println("Gracias por usar el sistema de facturación.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 5);

            sc.close();
        }
    }

    private static void agregarProducto(Scanner sc) {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = sc.next();
        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = sc.next();
        System.out.print("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();

        Producto producto = new Producto(productos.size() + 1, nombre, descripcion, precio);
        productos.add(producto);
        System.out.println("Producto agregado exitosamente.");
    }

    private static void agregarCliente(Scanner sc) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = sc.next();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = sc.next();
        System.out.print("Ingrese el email del cliente: ");
        String email = sc.next();
        System.out.print("Ingrese el tipo de cliente (Regular, VIP): ");
        String tipoCliente = sc.next();
        Cliente cliente = new Cliente(clientes.size() + 1, nombre, direccion, email, tipoCliente);
        clientes.add(cliente);
        System.out.println("Cliente agregado exitosamente.");
    }

    private static void crearFactura(Scanner sc) {
        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = sc.nextInt();
        Cliente cliente = buscarCliente(idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        List<DetalleFactura> detalles = new ArrayList<>();
        char agregarProducto;
        do {
            System.out.print("Ingrese el ID del producto: ");
            int idProducto = sc.nextInt();
            Producto producto = buscarProducto(idProducto);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
                return;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = sc.nextInt();

            DetalleFactura detalle = new DetalleFactura(detalles.size() + 1, producto, cantidad);
            detalles.add(detalle);

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            agregarProducto = sc.next().charAt(0);
        } while (agregarProducto == 's' || agregarProducto == 'S');

        Factura factura = new Factura(facturas.size() + 1, cliente, new Date(), detalles);
        facturas.add(factura);
        System.out.println("Factura creada exitosamente. Total: " + factura.getTotal());
    }

    private static Cliente buscarCliente(int idCliente) {
        return clientes.stream()
                .filter(cliente -> cliente.getIdCliente() == idCliente)
                .findFirst()
                .orElse(null);
    }

    private static Producto buscarProducto(int idProducto) {
        return productos.stream()
                .filter(producto -> producto.getIdProducto() == idProducto)
                .findFirst()
                .orElse(null);
    }

    private static void verFacturas() {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            return;
        }

        for (Factura factura : facturas) {
            System.out.println("Factura ID: " + factura.getIdFactura());
            System.out.println("Cliente: " + factura.getCliente().getNombre());
            System.out.println("Fecha: " + factura.getFecha());
            System.out.println("Detalles:");
            for (DetalleFactura detalle : factura.getDetalles()) {
                System.out.println("\tProducto: " + detalle.getProducto().getNombre() + 
                                   ", Cantidad: " + detalle.getCantidad() +
                                   ", Subtotal: " + detalle.getSubtotal());
            }
            System.out.println("Total: " + factura.getTotal());
            System.out.println("------------------------------");
        }
    }
}
