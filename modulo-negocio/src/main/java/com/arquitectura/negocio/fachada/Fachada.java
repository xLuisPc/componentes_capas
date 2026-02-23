package com.arquitectura.negocio.fachada;

import com.arquitectura.dto.ClienteDTO;
import com.arquitectura.dto.DocumentoDTO;
import com.arquitectura.negocio.servicio.ServicioClientes;
import com.arquitectura.negocio.servicio.ServicioDocumentos;
import com.arquitectura.persistencia.IRepositorioCliente;
import com.arquitectura.persistencia.IRepositorioDocumento;

import java.util.List;

/**
 * Implementacion del patron Fachada.
 * Oculta la complejidad de los subsistemas (SistemaClientes y GestorDocumentos)
 * y expone operaciones simplificadas al Controlador.
 * (Adaptado del codigo original: patron.facade.ImplementacionFachada)
 *
 * Patrones:
 *   - Facade: simplifica el acceso a los servicios de negocio.
 *
 * Principio SOLID:
 *   - Single Responsibility: orquesta, no implementa logica.
 *   - Open/Closed: nuevas operaciones se agregan sin modificar las existentes.
 *   - Dependency Inversion: depende de interfaces (IRepositorioCliente, IRepositorioDocumento).
 */
public class Fachada implements IFachada {

    private final ServicioClientes servicioClientes;
    private final ServicioDocumentos servicioDocumentos;

    /**
     * Inyeccion de dependencias por constructor.
     * Recibe las interfaces de repositorio, no las implementaciones concretas.
     */
    public Fachada(IRepositorioCliente repoCliente, IRepositorioDocumento repoDocumento) {
        this.servicioClientes = new ServicioClientes(repoCliente);
        this.servicioDocumentos = new ServicioDocumentos(repoDocumento);
    }

    // --- SistemaClientes ---

    @Override
    public void registrarCliente(ClienteDTO clienteDTO) {
        servicioClientes.registrarCliente(clienteDTO);
    }

    @Override
    public List<String> listarClientes() {
        return servicioClientes.listarClientes();
    }

    @Override
    public String obtenerInformacionCliente(ClienteDTO clienteDTO) {
        return servicioClientes.obtenerInformacionCliente(clienteDTO);
    }

    // --- GestorDocumentos ---

    @Override
    public void crearDocumento(DocumentoDTO documentoDTO) {
        servicioDocumentos.crearDocumento(documentoDTO);
    }

    @Override
    public List<String> listarDocumentos() {
        return servicioDocumentos.listarDocumentos();
    }

    @Override
    public void visualizarDocumento(DocumentoDTO documentoDTO) {
        servicioDocumentos.visualizarDocumento(documentoDTO);
    }
}
