package logica;

import dto.ProductoDTO;
import gestioninventario.entidades.Producto;
import gestioninventario.persistencia.ProductoDAOImpl;

import java.util.List;

/**
 * Lógica de Negocio exclusiva para GestionInventario.
 * Acceso directo y acoplado a ProductoDAOImpl (sin interfaz, sin Fachada).
 *
 * Esta clase demuestra el contraste con el patrón Fachada:
 * aquí no hay capa de abstracción entre la lógica y la persistencia.
 */
public class LogicaNegocio {

    // Acoplado directo: usa la clase concreta ProductoDAOImpl
    private final ProductoDAOImpl productoDAO;

    public LogicaNegocio() {
        // Obtiene la instancia Singleton directamente
        this.productoDAO = ProductoDAOImpl.getInstance();
    }

    /**
     * Crea un producto a partir del DTO.
     * Usa Builder para construir la entidad Producto.
     */
    public boolean crearProducto(ProductoDTO productoDTO) {
        // Builder Pattern para construir la entidad
        Producto producto = new Producto.Builder()
                .id(productoDTO.getId())
                .nombre(productoDTO.getNombre())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();
        return productoDAO.guardar(producto);
    }

    /**
     * Lista todos los productos desde la base de datos.
     */
    public List<ProductoDTO> listarProductos() {
        return productoDAO.listadoProductos();
    }
}
