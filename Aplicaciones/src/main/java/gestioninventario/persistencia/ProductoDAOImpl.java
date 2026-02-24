package gestioninventario.persistencia;

import dto.ProductoDTO;
import gestioninventario.entidades.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de Producto para MySQL (db_inventario).
 * Acoplado directo — LogicaNegocio lo usa sin pasar por interfaz ni Fachada.
 * Patrón Singleton.
 *
 * Conexión al contenedor Docker:
 *   URL:      jdbc:mysql://localhost:3306/db_inventario
 *   Usuario:  ccc_user
 *   Password: ccc_pass
 */
public class ProductoDAOImpl {

    private static ProductoDAOImpl instancia;

    private static final String URL      = "jdbc:mysql://localhost:3306/db_inventario?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO  = "ccc_user";
    private static final String PASSWORD = "ccc_pass";

    private ProductoDAOImpl() { }

    // ===================== SINGLETON =====================
    public static ProductoDAOImpl getInstance() {
        if (instancia == null) {
            instancia = new ProductoDAOImpl();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public boolean guardar(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getCantidad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar producto: " + e.getMessage());
            return false;
        }
    }

    public List<ProductoDTO> listadoProductos() {
        List<ProductoDTO> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, precio, cantidad FROM productos";
        try (Connection conn = obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ProductoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }
}
