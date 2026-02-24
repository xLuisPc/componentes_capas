package gestordocumentos;

import dto.DocumentoDTO;
import gestordocumentos.componentes.Documento;
import gestordocumentos.componentes.DocumentoHtml;
import gestordocumentos.componentes.DocumentoPdfAdaptado;
import gestordocumentos.componentes.DocumentoTexto;
import gestordocumentos.persistencia.DocumentoDAO;
import gestordocumentos.persistencia.DocumentoDAOImpl;

import java.util.List;

/**
 * Implementación de IGestorDocumentos.
 * Sin Fachada interna — accede directamente a los componentes y la persistencia.
 *
 * Usa el patrón Factory Method para crear el tipo de Documento correcto
 * según el tipo indicado en el DTO.
 */
public class GestorDocumentosImpl implements IGestorDocumentos {

    private final DocumentoDAO documentoDAO;

    // Inyección de Dependencias por constructor
    public GestorDocumentosImpl(DocumentoDAO documentoDAO) {
        this.documentoDAO = documentoDAO;
    }

    /** Constructor sin argumentos: obtiene el Singleton del DAO */
    public GestorDocumentosImpl() {
        this.documentoDAO = DocumentoDAOImpl.getInstance();
    }

    @Override
    public boolean crearDocumento(DocumentoDTO documentoDTO) {
        // Factory Method: crea el componente según el tipo
        Documento doc = crearComponente(documentoDTO.getTipo(), documentoDTO.getContenido());
        if (doc == null) {
            System.err.println("Tipo de documento desconocido: " + documentoDTO.getTipo());
            return false;
        }
        doc.dibujar();
        doc.imprimir();
        // Persiste el DTO en H2
        return documentoDAO.guardar(documentoDTO);
    }

    @Override
    public List<DocumentoDTO> listarDocumentos() {
        return documentoDAO.listadoDocumentos();
    }

    // ===================== FACTORY METHOD =====================
    private Documento crearComponente(String tipo, String contenido) {
        Documento doc;
        switch (tipo.toUpperCase()) {
            case "HTML":
                doc = new DocumentoHtml();
                break;
            case "TEXTO":
                doc = new DocumentoTexto();
                break;
            case "PDF":
                doc = new DocumentoPdfAdaptado();
                break;
            default:
                return null;
        }
        doc.setContenido(contenido);
        return doc;
    }
}
