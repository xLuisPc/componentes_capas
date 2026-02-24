package sistemasclientes.fachada;

import dto.PersonaDTO;
import java.util.List;

/**
 * Interfaz expuesta por SistemaClientes hacia la Fachada externa.
 * Principio SOLID: Dependency Inversion — la Fachada depende de esta
 * abstracción, no de la implementación concreta.
 */
public interface ISistemaClientes {
    boolean crearPersona(PersonaDTO personaDTO);
    List<PersonaDTO> listarPersonas();
}
