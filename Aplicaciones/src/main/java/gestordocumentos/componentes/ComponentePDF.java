package gestordocumentos.componentes;

/**
 * Componente PDF de bajo nivel (copiado de App2).
 * No implementa Documento directamente — expone su propia API.
 * Se usa a través del Adapter DocumentoPdfAdaptado.
 */
public class ComponentePDF {

    private String contenido;

    public void pdfFijaContenido(String contenido) {
        this.contenido = contenido;
    }

    public void pdfPreparaVisualizacion() {
        System.out.println("[PDF] Visualiza PDF: Inicio");
    }

    public void pdfRefresca() {
        System.out.println("[PDF] Visualiza contenido PDF: " + contenido);
    }

    public void pdfFinalizarVisualizacion() {
        System.out.println("[PDF] Visualiza PDF: Fin");
    }

    public void pdfEnviarImpresora() {
        System.out.println("[PDF] Impresion contenido PDF: " + contenido);
    }
}
