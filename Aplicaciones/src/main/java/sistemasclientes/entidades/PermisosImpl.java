package sistemasclientes.entidades;

/**
 * Implementación Singleton de Permisos para SistemaClientes.
 */
public class PermisosImpl implements Permisos {

    private static PermisosImpl instancia;

    private PermisosImpl() { }

    public static PermisosImpl getInstance() {
        if (instancia == null) {
            instancia = new PermisosImpl();
        }
        return instancia;
    }

    @Override
    public void validarPermisos() {
        System.out.println("[Permisos] Permiso validado para operacion en SistemaClientes.");
    }
}
