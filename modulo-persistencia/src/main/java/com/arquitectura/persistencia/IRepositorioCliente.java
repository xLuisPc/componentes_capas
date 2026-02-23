package com.arquitectura.persistencia;

import com.arquitectura.entidades.Cliente;
import java.util.List;

/**
 * Interfaz del repositorio de Clientes.
 * Principio SOLID - Dependency Inversion: las capas superiores
 * dependen de esta abstraccion, no de MySQL ni H2 directamente.
 */
public interface IRepositorioCliente {

    void guardar(Cliente cliente);

    List<Cliente> listarTodos();

    Cliente buscarPorId(double id);
}
