package gestordocumentos.persistencia;

import dto.DocumentoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de DocumentoDAO para H2 embebida.
 * Patrón Singleton: una única instancia.
 *
 * H2 en modo en memoria: los datos viven mientras la app está activa.
 * La tabla se crea automáticamente al inicializar.
 */
public class DocumentoDAOImpl implements DocumentoDAO {

    private static DocumentoDAOImpl instancia;

    private static final String URL      = "jdbc:h2:mem:db_documentos;DB_CLOSE_DELAY=-1";
    private static final String USUARIO  = "sa";
    private static final String PASSWORD = "";

    private DocumentoDAOImpl() {
        inicializarTabla();
    }

    // ===================== SINGLETON =====================
    public static DocumentoDAOImpl getInstance() {
        if (instancia == null) {
            instancia = new DocumentoDAOImpl();
        }
        return instancia;
    }

    /** Crea la tabla si no existe e inserta datos de ejemplo. */
    private void inicializarTabla() {
        String createSQL = "CREATE TABLE IF NOT EXISTS documentos ("
                + "id       INT AUTO_INCREMENT PRIMARY KEY,"
                + "tipo     VARCHAR(20)  NOT NULL,"
                + "contenido VARCHAR(500) NOT NULL)";
        String insertSQL = "INSERT INTO documentos (tipo, contenido) VALUES "
                + "('HTML',  'Pagina principal del sistema'),"
                + "('TEXTO', 'Reporte de actividades del mes'),"
                + "('PDF',   'Manual de usuario v1.0'),"
                + "('HTML',  'Formulario de contacto'),"
                + "('TEXTO', 'Notas de la reunion')";
        try (Connection conn = obtenerConexion();
             Statement st = conn.createStatement()) {
            st.execute(createSQL);
            // Insertar solo si la tabla está vacía
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM documentos");
            if (rs.next() && rs.getInt(1) == 0) {
                st.execute(insertSQL);
            }
        } catch (SQLException e) {
            System.err.println("Error al inicializar tabla documentos: " + e.getMessage());
        }
    }

    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    @Override
    public boolean guardar(DocumentoDTO documentoDTO) {
        String sql = "INSERT INTO documentos (tipo, contenido) VALUES (?, ?)";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, documentoDTO.getTipo());
            ps.setString(2, documentoDTO.getContenido());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar documento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<DocumentoDTO> listadoDocumentos() {
        List<DocumentoDTO> lista = new ArrayList<>();
        String sql = "SELECT tipo, contenido FROM documentos";
        try (Connection conn = obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new DocumentoDTO(
                        rs.getString("tipo"),
                        rs.getString("contenido")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar documentos: " + e.getMessage());
        }
        return lista;
    }
}
