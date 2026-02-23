package Factory;

import App2Interfaces.Documento;
import App2ComponenteHTML.DocumentoHtml;
import App2ComponenteTextoPlano.DocumentoTexto;
import App2ComponentePDFAdaptado.DocumentoPdfAdaptado;

/**
 * Fábrica de Documentos (Factory Method).
 * Centraliza la creación de los distintos tipos de Documento.
 *
 * Patrones aplicados:
 *   - Factory Method: métodos de creación para cada tipo de documento.
 *
 * Principios SOLID:
 *   - Single Responsibility: solo se encarga de crear documentos.
 *   - Open/Closed: se pueden agregar nuevos tipos sin modificar los existentes.
 *   - Dependency Inversion: retorna la interfaz Documento, no implementaciones concretas.
 */
public class FabricaDocumento {

    public static Documento crearDocumentoHTML(String contenido) {
        Documento doc = new DocumentoHtml();
        doc.setContenido(contenido);
        return doc;
    }

    public static Documento crearDocumentoTexto(String contenido) {
        Documento doc = new DocumentoTexto();
        doc.setContenido(contenido);
        return doc;
    }

    public static Documento crearDocumentoPDF(String contenido) {
        Documento doc = new DocumentoPdfAdaptado();
        doc.setContenido(contenido);
        return doc;
    }
}
