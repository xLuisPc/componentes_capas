package com.arquitectura.gestordocumentos;

/**
 * Componente PDF original (Adaptee para el patron Adapter).
 * (Adaptado del codigo original: App2ComponentePDF.ComponentePDF)
 */
public class ComponentePDF {

    private String contenido;

    public void pdfFijaContenido(String contenido) {
        this.contenido = contenido;
    }

    public void pdfPreparaVisualizacion() {
        System.out.println("Visualiza PDF: Inicio ");
    }

    public void pdfRefresca() {
        System.out.println("Visualiza contenido PDF: " + contenido);
    }

    public void pdfFinalizarVisualizacion() {
        System.out.println("Visualiza PDF: Fin ");
    }

    public void pdfEnviarImpresora() {
        System.out.println("Impresion contenido PDF: " + contenido);
    }
}
