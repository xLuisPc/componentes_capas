package gestordocumentos.persistencia;

import dto.DocumentoDTO;
import java.util.List;

/**
 * Interfaz DAO para Documento.
 * Principio SOLID: Dependency Inversion.
 */
public interface DocumentoDAO {
    boolean guardar(DocumentoDTO documentoDTO);
    List<DocumentoDTO> listadoDocumentos();
}
