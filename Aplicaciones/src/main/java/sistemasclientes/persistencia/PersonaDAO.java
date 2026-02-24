package sistemasclientes.persistencia;

import dto.PersonaDTO;
import sistemasclientes.entidades.Persona;
import java.util.List;

/**
 * Interfaz DAO para Persona.
 * Principio SOLID: Dependency Inversion — las capas superiores dependen
 * de esta abstracción, no de implementaciones concretas de BD.
 */
public interface PersonaDAO {
    boolean guardar(Persona persona);
    List<PersonaDTO> listadoPersonas();
}
