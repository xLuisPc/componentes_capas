package com.arquitectura.gestordocumentos;

import com.arquitectura.entidades.Documento;
import com.arquitectura.persistencia.IRepositorioDocumento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion del repositorio de Documentos usando H2 Database (embebida).
 * Componente GestorDocumentos -> Persistencia -> Base de Datos H2.
 *
 * Principio SOLID:
 *   - Single Responsibility: solo gestiona la persistencia de documentos en H2.
 *   - Liskov Substitution: intercambiable con cualquier IRepositorioDocumento.
 */
public class RepositorioDocumentoH2 implements IRepositorioDocumento {

    // H2 en memoria, se mantiene mientras la JVM este activa
    private static final String URL = "jdbc:h2:mem:gestor_documentos;DB_CLOSE_DELAY=-1";
    private static final String USUARIO = "sa";
    private static final String CLAVE = "";

    public RepositorioDocumentoH2() {
        cargarDriver();
        crearTabla();
    }

    private void cargarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[H2] Error: Driver H2 no encontrado en el classpath.");
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    private void crearTabla() {
        String sql = """
            CREATE TABLE IF NOT EXISTS documentos (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                tipo VARCHAR(20) NOT NULL,
                contenido CLOB NOT NULL
            )
            """;
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("[H2] Error al crear tabla documentos: " + e.getMessage());
        }
    }

    @Override
    public void guardar(Documento documento) {
        String sql = "INSERT INTO documentos (tipo, contenido) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, documento.getTipo());
            ps.setString(2, documento.getContenido());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                documento.setId(keys.getLong(1));
            }
            System.out.println("[H2] Documento guardado: " + documento.getTipo() + " (id=" + documento.getId() + ")");
        } catch (SQLException e) {
            System.err.println("[H2] Error al guardar documento: " + e.getMessage());
        }
    }

    @Override
    public List<Documento> listarTodos() {
        List<Documento> lista = new ArrayList<>();
        String sql = "SELECT * FROM documentos";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Documento d = new Documento(
                    rs.getLong("id"),
                    rs.getString("tipo"),
                    rs.getString("contenido")
                );
                lista.add(d);
            }
        } catch (SQLException e) {
            System.err.println("[H2] Error al listar documentos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Documento buscarPorId(long id) {
        String sql = "SELECT * FROM documentos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Documento(
                    rs.getLong("id"),
                    rs.getString("tipo"),
                    rs.getString("contenido")
                );
            }
        } catch (SQLException e) {
            System.err.println("[H2] Error al buscar documento: " + e.getMessage());
        }
        return null;
    }
}
