package gestordocumentos.componentes;

/**
 * Adapter para ComponentePDF (copiado de App2).
 * Adapta la API de ComponentePDF a la interfaz Documento.
 *
 * Patrón Adapter: permite que ComponentePDF (incompatible) sea
 * usado como Documento (interfaz del sistema).
 */
public class DocumentoPdfAdaptado implements Documento {

    private final ComponentePDF herramientaPdf = new ComponentePDF();

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
