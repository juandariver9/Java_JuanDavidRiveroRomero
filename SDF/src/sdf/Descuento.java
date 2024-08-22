package sdf;

import java.util.function.Function;

public class Descuento {
    private int idDescuento;
    private Factura factura;
    private double porcentajeDescuento;
    private double montoDescuento;

    public Descuento(int idDescuento, Factura factura, double porcentajeDescuento) {
        this.idDescuento = idDescuento;
        this.factura = factura;
        this.porcentajeDescuento = porcentajeDescuento;
        this.montoDescuento = calcularDescuento();
    }

    private double calcularDescuento() {
        Function<Double, Double> descuento = total -> total * (porcentajeDescuento / 100);
        return descuento.apply(factura.getTotal());
    }

    // Getters y setters

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }
}
