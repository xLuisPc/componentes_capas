package com.arquitectura.entidades;

/**
 * Entidad de dominio: Documento.
 * Representa un documento dentro del sistema (GestorDocumentos).
 */
public class Documento {

    private long id;
    private String tipo;       // HTML, TEXTO, PDF
    private String contenido;

    public Documento() {
    }

    public Documento(long id, String tipo, String contenido) {
        this.id = id;
        this.tipo = tipo;
        this.contenido = contenido;
    }

    // --- Getters y Setters ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Documento{id=" + id +
               ", tipo='" + tipo + '\'' +
               ", contenido='" + contenido + '\'' + '}';
    }
}
