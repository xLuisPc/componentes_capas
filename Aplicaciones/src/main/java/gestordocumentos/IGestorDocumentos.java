package gestordocumentos;

import dto.DocumentoDTO;
import java.util.List;

/**
 * Interfaz expuesta por GestorDocumentos hacia la Fachada externa.
 * Principio SOLID: Dependency Inversion.
 */
public interface IGestorDocumentos {
    boolean crearDocumento(DocumentoDTO documentoDTO);
    List<DocumentoDTO> listarDocumentos();
}
