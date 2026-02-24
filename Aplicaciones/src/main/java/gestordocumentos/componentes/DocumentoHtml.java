package gestordocumentos.componentes;

/**
 * Componente DocumentoHtml (copiado de App2).
 * Implementa la interfaz Documento para documentos de tipo HTML.
 */
public class DocumentoHtml implements Documento {

    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("[HTML] Dibujando documento HTML: " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("[HTML] Imprimiendo documento HTML: " + contenido);
    }
}
