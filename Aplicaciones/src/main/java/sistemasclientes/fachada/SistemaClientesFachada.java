package sistemasclientes.fachada;

import dto.PersonaDTO;
import sistemasclientes.entidades.Persona;
import sistemasclientes.entidades.Permisos;
import sistemasclientes.entidades.PermisosImpl;
import sistemasclientes.persistencia.PersonaDAO;
import sistemasclientes.persistencia.PersonaDAOImpl;

import java.util.List;

/**
 * Fachada interna de SistemaClientes.
 * Implementa ISistemaClientes y oculta los detalles de entidades y persistencia.
 *
 * Patrones aplicados:
 *   - Facade: oculta la complejidad de Persona + PersonaDAO + Permisos.
 *   - Builder: usa Persona.Builder para construir la entidad.
 *   - Singleton: usa las instancias Singleton de PermisosImpl y PersonaDAOImpl.
 *
 * Principios SOLID:
 *   - Single Responsibility: solo gestiona la lógica de SistemaClientes.
 *   - Dependency Inversion: depende de interfaces (PersonaDAO, Permisos).
 */
public class SistemaClientesFachada implements ISistemaClientes {

    private final Permisos   permisos;
    private final PersonaDAO personaDAO;

    // Inyección de Dependencias por constructor
    public SistemaClientesFachada(Permisos permisos, PersonaDAO personaDAO) {
        this.permisos   = permisos;
        this.personaDAO = personaDAO;
    }

    /** Constructor sin argumentos: obtiene los Singleton internos */
    public SistemaClientesFachada() {
        this.permisos   = PermisosImpl.getInstance();
        this.personaDAO = PersonaDAOImpl.getInstance();
    }

    @Override
    public boolean crearPersona(PersonaDTO personaDTO) {
        permisos.validarPermisos();
        // Builder Pattern para construir la entidad Persona
        Persona persona = new Persona.Builder()
                .identificacion(personaDTO.getIdentificacion())
                .nombres(personaDTO.getNombres())
                .apellidos(personaDTO.getApellidos())
                .edad(personaDTO.getEdad())
                .build();
        return personaDAO.guardar(persona);
    }

    @Override
    public List<PersonaDTO> listarPersonas() {
        return personaDAO.listadoPersonas();
    }
}
