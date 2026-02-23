package com.arquitectura.gestordocumentos;

/**
 * Adaptador para el componente PDF.
 * Patron Adapter: adapta ComponentePDF a la interfaz IDocumentoComponente.
 * (Adaptado del codigo original: App2ComponentePDFAdaptado.DocumentoPdfAdaptado)
 */
public class DocumentoPdfAdaptado implements IDocumentoComponente {

    private ComponentePDF herramientaPdf = new ComponentePDF();

    @Override
    public void setContenido(String contenido) {
        herramientaPdf.pdfFijaContenido(contenido);
    }

    @Override
    public void dibujar() {
        herramientaPdf.pdfPreparaVisualizacion();
        herramientaPdf.pdfRefresca();
        herramientaPdf.pdfFinalizarVisualizacion();
    }

    @Override
    public void imprimir() {
        herramientaPdf.pdfEnviarImpresora();
    }
}
