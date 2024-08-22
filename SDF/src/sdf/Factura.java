package sdf;

import java.util.Date;
import java.util.List;

public class Factura {
    private int idFactura;
    private Cliente cliente;
    private Date fecha;
    private double total;
    private List<DetalleFactura> detalles;

    public Factura(int idFactura, Cliente cliente, Date fecha, List<DetalleFactura> detalles) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.fecha = fecha;
        this.detalles = detalles;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        return detalles.stream().mapToDouble(DetalleFactura::getSubtotal).sum();
    }

    // Getters y setters

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
