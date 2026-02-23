package com.arquitectura.negocio.fachada;

import com.arquitectura.dto.ClienteDTO;
import com.arquitectura.dto.DocumentoDTO;
import java.util.List;

/**
 * Interfaz de la Fachada.
 * El Controlador se conecta a la Fachada SOLO a traves de esta interfaz.
 * (Adaptada del codigo original: patron.facade.IFacade)
 *
 * Principio SOLID - Dependency Inversion:
 *   El controlador depende de esta abstraccion, no de la implementacion concreta.
 */
public interface IFachada {

    // --- Operaciones de SistemaClientes ---

    void registrarCliente(ClienteDTO clienteDTO);

    List<String> listarClientes();

    String obtenerInformacionCliente(ClienteDTO clienteDTO);

    // --- Operaciones de GestorDocumentos ---

    void crearDocumento(DocumentoDTO documentoDTO);

    List<String> listarDocumentos();

    void visualizarDocumento(DocumentoDTO documentoDTO);
}
