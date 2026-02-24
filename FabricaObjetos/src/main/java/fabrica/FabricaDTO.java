package fabrica;

import dto.DocumentoDTO;
import dto.PersonaDTO;
import dto.ProductoDTO;

/**
 * Fábrica de DTOs (Factory Method + Singleton).
 * Centraliza la creación de todos los DTOs de la aplicación.
 * Visible para todas las capas (transversal).
 *
 * Patrones aplicados:
 *   - Singleton: una única instancia de la fábrica.
 *   - Factory Method: métodos de creación para cada tipo de DTO.
 *
 * Principios SOLID:
 *   - Single Responsibility: solo crea DTOs.
 *   - Open/Closed: se pueden agregar nuevos métodos sin modificar existentes.
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

    // ===================== FACTORY METHODS =====================

    /**
     * Crea un PersonaDTO usando el patrón Builder.
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
     * @param tipo "HTML", "TEXTO" o "PDF"
     */
    public DocumentoDTO crearDocumentoDTO(String tipo, String contenido) {
        return new DocumentoDTO.Builder()
                .tipo(tipo)
                .contenido(contenido)
                .build();
    }
}
