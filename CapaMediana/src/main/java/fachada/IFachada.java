package fachada;

import dto.DocumentoDTO;
import dto.PersonaDTO;

import java.util.List;

/**
 * Interfaz entre el Controlador y la Fachada.
 * El Controlador depende de esta abstracción, no de la implementación concreta.
 *
 * Principio SOLID: Dependency Inversion.
 * Cubre los dominios SistemaClientes y GestorDocumentos.
 * GestionInventario va por LogicaNegocio directamente (sin esta interfaz).
 */
public interface IFachada {

    // ── SistemaClientes ──────────────────────────────────────────
    boolean      crearPersona(PersonaDTO personaDTO);
    List<PersonaDTO> listarPersonas();

    // ── GestorDocumentos ─────────────────────────────────────────
    boolean          crearDocumento(DocumentoDTO documentoDTO);
    List<DocumentoDTO> listarDocumentos();
}
