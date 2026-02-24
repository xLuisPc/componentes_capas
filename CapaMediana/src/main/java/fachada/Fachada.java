package fachada;

import dto.DocumentoDTO;
import dto.PersonaDTO;
import gestordocumentos.GestorDocumentosImpl;
import gestordocumentos.IGestorDocumentos;
import sistemasclientes.fachada.ISistemaClientes;
import sistemasclientes.fachada.SistemaClientesFachada;

import java.util.List;

/**
 * Implementación de IFachada.
 * Coordina SistemaClientes y GestorDocumentos.
 * Recibe sus implementaciones por inyección de dependencias (constructor).
 *
 * Patrones aplicados:
 *   - Facade: simplifica el acceso a los dos subsistemas.
 *   - Dependency Inversion: depende de ISistemaClientes e IGestorDocumentos.
 */
public class Fachada implements IFachada {

    private final ISistemaClientes   sistemaClientes;
    private final IGestorDocumentos  gestorDocumentos;

    // Inyección de Dependencias por constructor
    public Fachada(ISistemaClientes sistemaClientes, IGestorDocumentos gestorDocumentos) {
        this.sistemaClientes  = sistemaClientes;
        this.gestorDocumentos = gestorDocumentos;
    }

    /** Constructor sin argumentos: instancia los subsistemas con sus Singleton */
    public Fachada() {
        this.sistemaClientes  = new SistemaClientesFachada();
        this.gestorDocumentos = new GestorDocumentosImpl();
    }

    // ── SistemaClientes ──────────────────────────────────────────
    @Override
    public boolean crearPersona(PersonaDTO personaDTO) {
        return sistemaClientes.crearPersona(personaDTO);
    }

    @Override
    public List<PersonaDTO> listarPersonas() {
        return sistemaClientes.listarPersonas();
    }

    // ── GestorDocumentos ─────────────────────────────────────────
    @Override
    public boolean crearDocumento(DocumentoDTO documentoDTO) {
        return gestorDocumentos.crearDocumento(documentoDTO);
    }

    @Override
    public List<DocumentoDTO> listarDocumentos() {
        return gestorDocumentos.listarDocumentos();
    }
}
