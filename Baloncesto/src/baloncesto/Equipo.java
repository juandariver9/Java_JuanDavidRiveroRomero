package baloncesto;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private String ciudad;
    private String fundado;

    // Constructor
    public Equipo(int idEquipo, String nombre, String ciudad, String fundado) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fundado = fundado;
    }

    // Getters y Setters
    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFundado() {
        return fundado;
    }

    public void setFundado(String fundado) {
        this.fundado = fundado;
    }

    @Override
    public String toString() {
        return "Equipo [ID: " + idEquipo + ", Nombre: " + nombre + ", Ciudad: " + ciudad + ", Fundado: " + fundado + "]";
    }
}
