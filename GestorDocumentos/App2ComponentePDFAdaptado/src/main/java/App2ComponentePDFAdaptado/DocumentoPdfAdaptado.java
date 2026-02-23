package App2ComponentePDFAdaptado;

import App2Interfaces.Documento;
import App2ComponentePDF.ComponentePDF;

public class DocumentoPdfAdaptado implements Documento {
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
