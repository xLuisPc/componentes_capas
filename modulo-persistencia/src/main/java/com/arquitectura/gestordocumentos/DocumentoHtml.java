package com.arquitectura.gestordocumentos;

/**
 * Componente de documento HTML.
 * (Adaptado del codigo original: App2ComponenteHTML.DocumentoHtml)
 */
public class DocumentoHtml implements IDocumentoComponente {

    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando DocumentoHTML en la aplicación. " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo DocumentoHTML. " + contenido);
    }
}
