package sistemasclientes.persistencia;

import dto.PersonaDTO;
import sistemasclientes.entidades.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de PersonaDAO para MySQL (db_clientes).
 * Patrón Singleton: una única conexión compartida.
 *
 * Conexión al contenedor Docker:
 *   URL:      jdbc:mysql://localhost:3306/db_clientes
 *   Usuario:  ccc_user
 *   Password: ccc_pass
 */
public class PersonaDAOImpl implements PersonaDAO {

    private static PersonaDAOImpl instancia;

    private static final String URL      = "jdbc:mysql://localhost:3306/db_clientes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO  = "ccc_user";
    private static final String PASSWORD = "ccc_pass";

    private PersonaDAOImpl() { }

    // ===================== SINGLETON =====================
    public static PersonaDAOImpl getInstance() {
        if (instancia == null) {
            instancia = new PersonaDAOImpl();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    @Override
    public boolean guardar(Persona persona) {
        String sql = "INSERT INTO personas (identificacion, nombres, apellidos, edad) VALUES (?, ?, ?, ?)";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, persona.getIdentificacion());
            ps.setString(2, persona.getNombres());
            ps.setString(3, persona.getApellidos());
            ps.setInt(4, persona.getEdad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar persona: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<PersonaDTO> listadoPersonas() {
        List<PersonaDTO> lista = new ArrayList<>();
        String sql = "SELECT identificacion, nombres, apellidos, edad FROM personas";
        try (Connection conn = obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new PersonaDTO(
                        rs.getDouble("identificacion"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getInt("edad")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar personas: " + e.getMessage());
        }
        return lista;
    }
}
