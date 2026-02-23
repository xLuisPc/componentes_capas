package com.arquitectura.gestordocumentos;

/**
 * Interfaz para los componentes de documento.
 * (Adaptada del codigo original: App2Interfaces.Documento)
 *
 * Principio SOLID - Interface Segregation: interfaz especifica para documentos.
 */
public interface IDocumentoComponente {

    void setContenido(String contenido);

    void dibujar();

    void imprimir();
}
