package com.arquitectura.negocio.controlador;

import com.arquitectura.dto.ClienteDTO;
import com.arquitectura.dto.DocumentoDTO;
import com.arquitectura.fabrica.FabricaDeObjetos;
import com.arquitectura.negocio.fachada.IFachada;

import java.util.List;

/**
 * Componente Controlador.
 * Recibe peticiones de las vistas (consola/escritorio) y las delega a la Fachada.
 * Se conecta a la Fachada SOLO por medio de la interfaz IFachada.
 *
 * Patrones:
 *   - Usa FabricaDeObjetos (Singleton) para crear DTOs.
 *
 * Principio SOLID:
 *   - Dependency Inversion: depende de IFachada (interfaz), no de Fachada (implementacion).
 *   - Single Responsibility: solo coordina entre la vista y la fachada.
 */
public class Controlador {

    private final IFachada fachada;         // Conexion por interfaz
    private final FabricaDeObjetos fabrica; // Singleton

    /**
     * Inyeccion de dependencias por constructor.
     * El controlador NO sabe que implementacion de IFachada recibe.
     */
    public Controlador(IFachada fachada) {
        this.fachada = fachada;
        this.fabrica = FabricaDeObjetos.getInstance();
    }

    // ==================== SISTEMA CLIENTES ====================

    /**
     * Registrar un cliente nuevo usando el Builder de la Fabrica.
     */
    public void registrarCliente(double id, String nombres, String apellidos,
                                  String email, String mensajeBienvenida) {
        ClienteDTO dto = fabrica.builderClienteDTO()
                .conId(id)
                .conNombres(nombres)
                .conApellidos(apellidos)
                .conEmail(email)
                .conMensajeBienvenida(mensajeBienvenida)
                .build();

        fachada.registrarCliente(dto);
    }

    /**
     * Listar todos los clientes registrados.
     */
    public List<String> listarClientes() {
        return fachada.listarClientes();
    }

    /**
     * Obtener informacion consolidada del ultimo cliente registrado.
     */
    public String obtenerInfoCliente(double id, String nombres, String apellidos,
                                      String email, String mensaje) {
        ClienteDTO dto = fabrica.crearClienteDTO(id, nombres, apellidos, email, mensaje);
        return fachada.obtenerInformacionCliente(dto);
    }

    // ==================== GESTOR DOCUMENTOS ====================

    /**
     * Crear un nuevo documento usando el Builder de la Fabrica.
     */
    public void crearDocumento(String tipo, String contenido) {
        DocumentoDTO dto = fabrica.builderDocumentoDTO()
                .conTipo(tipo)
                .conContenido(contenido)
                .build();

        fachada.crearDocumento(dto);
    }

    /**
     * Listar todos los documentos guardados.
     */
    public List<String> listarDocumentos() {
        return fachada.listarDocumentos();
    }

    /**
     * Visualizar e imprimir un documento.
     */
    public void visualizarDocumento(String tipo, String contenido) {
        DocumentoDTO dto = fabrica.crearDocumentoDTO(0, tipo, contenido);
        fachada.visualizarDocumento(dto);
    }
}
