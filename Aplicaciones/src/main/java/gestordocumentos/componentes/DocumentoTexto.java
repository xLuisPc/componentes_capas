package gestordocumentos.componentes;

/**
 * Componente DocumentoTexto (copiado de App2).
 * Implementa la interfaz Documento para documentos de texto plano.
 */
public class DocumentoTexto implements Documento {

    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("[TEXTO] Dibujando documento de texto: " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("[TEXTO] Imprimiendo documento de texto: " + contenido);
    }
}
