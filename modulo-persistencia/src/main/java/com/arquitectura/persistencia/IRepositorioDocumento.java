package com.arquitectura.persistencia;

import com.arquitectura.entidades.Documento;
import java.util.List;

/**
 * Interfaz del repositorio de Documentos.
 * Principio SOLID - Dependency Inversion: las capas superiores
 * dependen de esta abstraccion, no de H2 directamente.
 */
public interface IRepositorioDocumento {

    void guardar(Documento documento);

    List<Documento> listarTodos();

    Documento buscarPorId(long id);
}
