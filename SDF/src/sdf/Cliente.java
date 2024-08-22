package sdf;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String direccion;
    private String email;
    private String tipoCliente;

    public Cliente(int idCliente, String nombre, String direccion, String email, String tipoCliente) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    // Getters y setters

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}
