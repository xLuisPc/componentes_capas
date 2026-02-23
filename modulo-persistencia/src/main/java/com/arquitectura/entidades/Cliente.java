package com.arquitectura.entidades;

/**
 * Entidad de dominio: Cliente.
 * Representa un cliente dentro del sistema (SistemaClientes).
 */
public class Cliente {

    private double id;
    private String nombres;
    private String apellidos;
    private String email;
    private String mensajeBienvenida;

    public Cliente() {
    }

    public Cliente(double id, String nombres, String apellidos,
                   String email, String mensajeBienvenida) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.mensajeBienvenida = mensajeBienvenida;
    }

    // --- Getters y Setters ---

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensajeBienvenida() {
        return mensajeBienvenida;
    }

    public void setMensajeBienvenida(String mensajeBienvenida) {
        this.mensajeBienvenida = mensajeBienvenida;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id +
               ", nombres='" + nombres + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", email='" + email + '\'' + '}';
    }
}
