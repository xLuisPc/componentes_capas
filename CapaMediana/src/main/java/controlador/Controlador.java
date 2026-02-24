package controlador;

import dto.DocumentoDTO;
import dto.PersonaDTO;
import dto.ProductoDTO;
import fachada.IFachada;
import logica.LogicaNegocio;

import java.util.List;

/**
 * Controlador central de la aplicación.
 * Recibe las llamadas de las Vistas y las redirige al componente correcto.
 *
 * Dos rutas de comunicación:
 *   1. IFachada → Fachada → SistemaClientes / GestorDocumentos
 *   2. LogicaNegocio → ProductoDAOImpl (GestionInventario, acoplado)
 *
 * Principios SOLID:
 *   - Dependency Inversion: depende de IFachada (abstracción), no de Fachada.
 *   - Single Responsibility: solo coordina, no contiene lógica de negocio.
 *
 * Inyección de Dependencias por constructor.
 */
public class Controlador {

    private final IFachada      fachada;
    private final LogicaNegocio logicaNegocio;

    public Controlador(IFachada fachada, LogicaNegocio logicaNegocio) {
        this.fachada       = fachada;
        this.logicaNegocio = logicaNegocio;
    }

    // ── SistemaClientes (vía IFachada) ───────────────────────────
    public boolean crearPersona(PersonaDTO personaDTO) {
        return fachada.crearPersona(personaDTO);
    }

    public List<PersonaDTO> listarPersonas() {
        return fachada.listarPersonas();
    }

    // ── GestorDocumentos (vía IFachada) ──────────────────────────
    public boolean crearDocumento(DocumentoDTO documentoDTO) {
        return fachada.crearDocumento(documentoDTO);
    }

    public List<DocumentoDTO> listarDocumentos() {
        return fachada.listarDocumentos();
    }

    // ── GestionInventario (vía LogicaNegocio, directo) ───────────
    public boolean crearProducto(ProductoDTO productoDTO) {
        return logicaNegocio.crearProducto(productoDTO);
    }

    public List<ProductoDTO> listarProductos() {
        return logicaNegocio.listarProductos();
    }
}
