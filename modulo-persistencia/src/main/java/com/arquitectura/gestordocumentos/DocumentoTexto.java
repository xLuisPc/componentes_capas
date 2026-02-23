package com.arquitectura.gestordocumentos;

/**
 * Componente de documento de texto plano.
 * (Adaptado del codigo original: App2ComponenteTextoPlano.DocumentoTexto)
 */
public class DocumentoTexto implements IDocumentoComponente {

    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando Documento en la aplicación. " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo Documento. " + contenido);
    }
}
