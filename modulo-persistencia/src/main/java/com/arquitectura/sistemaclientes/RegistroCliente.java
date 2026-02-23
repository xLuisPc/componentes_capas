package com.arquitectura.sistemaclientes;

import com.arquitectura.entidades.Cliente;

/**
 * Subsistema A del SistemaClientes - Registro de informacion del cliente.
 * (Adaptado del codigo original: subsistema.A.ClaseA)
 */
public class RegistroCliente {

    private double id;
    private String nombres;
    private String apellidos;

    public void cargarInformacionTerceros() {
        System.out.println("Información enviada al sistema contable.");
    }

    public void cargarDesdeEntidad(Cliente cliente) {
        this.id = cliente.getId();
        this.nombres = cliente.getNombres();
        this.apellidos = cliente.getApellidos();
    }

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
}
