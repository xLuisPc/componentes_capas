package fabrica;

import controlador.Controlador;
import dto.DocumentoDTO;
import dto.PersonaDTO;
import dto.ProductoDTO;
import fachada.Fachada;
import fachada.IFachada;
import logica.LogicaNegocio;

/**
 * Fábrica Centralizada de Objetos (Factory Method + Singleton).
 * Centraliza la creación de TODOS los objetos de la aplicación:
 *   - DTOs (transversales)
 *   - Componentes de CapaMediana (Controlador, Fachada, LogicaNegocio)
 *   - Vistas de Presentación (VistaConsola, VistaEscritorio)
 *
 * Patrones aplicados:
 *   - Singleton: una única instancia de la fábrica.
 *   - Factory Method: métodos de creación para cada tipo de objeto.
 *
 * Principios SOLID:
 *   - Single Responsibility: centraliza la creación de objetos.
 *   - Dependency Inversion: Inicio depende de esta fábrica, no de clases concretas.
 *   - Loose Coupling: Inicio no conoce detalles de instanciación de componentes.
 */
public class FabricaDTO {

    // ===================== SINGLETON =====================
    private static FabricaDTO instancia;

    private FabricaDTO() { }

    public static FabricaDTO getInstance() {
        if (instancia == null) {
            instancia = new FabricaDTO();
        }
        return instancia;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // ═════════════ FACTORY METHODS - DTOs (COMPONENTE: DTO) ═════════════════
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Crea un PersonaDTO usando el patrón Builder.
     * Componente: DTO → debe ser creado por la fábrica.
     */
    public PersonaDTO crearPersonaDTO(Double identificacion, String nombres,
                                      String apellidos, int edad) {
        return new PersonaDTO.Builder()
                .identificacion(identificacion)
                .nombres(nombres)
                .apellidos(apellidos)
                .edad(edad)
                .build();
    }

    /**
     * Crea un ProductoDTO usando el patrón Builder.
     * Componente: DTO → debe ser creado por la fábrica.
     */
    public ProductoDTO crearProductoDTO(int id, String nombre,
                                        double precio, int cantidad) {
        return new ProductoDTO.Builder()
                .id(id)
                .nombre(nombre)
                .precio(precio)
                .cantidad(cantidad)
                .build();
    }

    /**
     * Crea un DocumentoDTO usando el patrón Builder.
     * Componente: DTO → debe ser creado por la fábrica.
     * @param tipo "HTML", "TEXTO" o "PDF"
     */
    public DocumentoDTO crearDocumentoDTO(String tipo, String contenido) {
        return new DocumentoDTO.Builder()
                .tipo(tipo)
                .contenido(contenido)
                .build();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // ═════════════ FACTORY METHODS - CAPA MEDIA (COMPONENTE: CapaMediana) ═════
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Crea una Fachada (interfaz IFachada).
     * Componente: CapaMediana → coordina SistemaClientes y GestorDocumentos.
     *
     * @return Una nueva instancia de Fachada que implementa IFachada.
     */
    public IFachada crearFachada() {
        return new Fachada();
    }

    /**
     * Crea una instancia de LogicaNegocio.
     * Componente: CapaMediana → controla la lógica de negocio para GestionInventario.
     *
     * @return Una nueva instancia de LogicaNegocio.
     */
    public LogicaNegocio crearLogicaNegocio() {
        return new LogicaNegocio();
    }

    /**
     * Crea un Controlador central.
     * Componente: CapaMediana → orquesta la comunicación entre Presentación y la Fachada/LogicaNegocio.
     *
     * @return Una nueva instancia de Controlador con sus dependencias inyectadas.
     */
    public Controlador crearControlador() {
        IFachada fachada = crearFachada();
        LogicaNegocio logicaNegocio = crearLogicaNegocio();
        return new Controlador(fachada, logicaNegocio);
    }

}
