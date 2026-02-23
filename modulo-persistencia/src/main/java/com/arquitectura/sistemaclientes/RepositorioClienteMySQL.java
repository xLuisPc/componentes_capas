package com.arquitectura.sistemaclientes;

import com.arquitectura.entidades.Cliente;
import com.arquitectura.persistencia.IRepositorioCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion del repositorio de Clientes usando MySQL.
 * Componente SistemaClientes -> Persistencia -> Base de Datos MySQL.
 *
 * Principio SOLID:
 *   - Single Responsibility: solo gestiona la persistencia de clientes en MySQL.
 *   - Liskov Substitution: intercambiable con cualquier IRepositorioCliente.
 */
public class RepositorioClienteMySQL implements IRepositorioCliente {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_clientes";
    private static final String USUARIO = "app_user";
    private static final String CLAVE = "app_password";

    public RepositorioClienteMySQL() {
        cargarDriver();
        crearTabla();
    }

    private void cargarDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[MySQL] Error: Driver MySQL no encontrado en el classpath.");
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    private void crearTabla() {
        String sql = """
            CREATE TABLE IF NOT EXISTS clientes (
                id DOUBLE PRIMARY KEY,
                nombres VARCHAR(100) NOT NULL,
                apellidos VARCHAR(100) NOT NULL,
                email VARCHAR(150) NOT NULL,
                mensaje_bienvenida VARCHAR(255)
            )
            """;
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("[MySQL] Error al crear tabla clientes: " + e.getMessage());
        }
    }

    @Override
    public void guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (id, nombres, apellidos, email, mensaje_bienvenida) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, cliente.getId());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getMensajeBienvenida());
            ps.executeUpdate();
            System.out.println("[MySQL] Cliente guardado: " + cliente.getNombres());
        } catch (SQLException e) {
            System.err.println("[MySQL] Error al guardar cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getDouble("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("email"),
                    rs.getString("mensaje_bienvenida")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("[MySQL] Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Cliente buscarPorId(double id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getDouble("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("email"),
                    rs.getString("mensaje_bienvenida")
                );
            }
        } catch (SQLException e) {
            System.err.println("[MySQL] Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }
}
