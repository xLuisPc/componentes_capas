package com.arquitectura.negocio.servicio;

import com.arquitectura.dto.ClienteDTO;
import com.arquitectura.entidades.Cliente;
import com.arquitectura.persistencia.IRepositorioCliente;
import com.arquitectura.sistemaclientes.RegistroCliente;
import com.arquitectura.sistemaclientes.MensajeriaCliente;
import com.arquitectura.sistemaclientes.ConfiguracionCliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Logica de Negocio - Casos de Uso - Servicio para SistemaClientes.
 * Coordina los subsistemas de registro, mensajeria y configuracion.
 * (Logica adaptada del codigo original: ImplementacionFachada)
 *
 * Principio SOLID:
 *   - Single Responsibility: solo logica de negocio de clientes.
 */
public class ServicioClientes {

    private final IRepositorioCliente repositorio;

    // Subsistemas internos del SistemaClientes
    private RegistroCliente registroCliente;
    private MensajeriaCliente mensajeriaCliente;
    private ConfiguracionCliente configuracionCliente;

    public ServicioClientes(IRepositorioCliente repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Caso de uso: Registrar un nuevo cliente.
     * Orquesta los subsistemas A (registro), B (mensajeria), C (configuracion)
     * y persiste en la base de datos.
     */
    public void registrarCliente(ClienteDTO dto) {
        // Convertir DTO a Entidad
        Cliente cliente = new Cliente(
            dto.getId(), dto.getNombres(), dto.getApellidos(),
            dto.getEmail(), dto.getMensajeBienvenida()
        );

        // Subsistema A: Registro
        registroCliente = new RegistroCliente();
        registroCliente.cargarDesdeEntidad(cliente);
        registroCliente.cargarInformacionTerceros();

        // Subsistema B: Mensajeria
        mensajeriaCliente = new MensajeriaCliente(
            dto.getEmail(), dto.getMensajeBienvenida()
        );
        mensajeriaCliente.enviarMensaje();

        // Subsistema C: Configuracion
        configuracionCliente = new ConfiguracionCliente(
            dto.getId() + " :" + dto.getNombres() + " :" + dto.getApellidos() + "\n"
        );
        configuracionCliente.procesarInformacion();

        // Persistir en BD (MySQL)
        repositorio.guardar(cliente);
    }

    /**
     * Caso de uso: Listar todos los clientes registrados.
     */
    public List<String> listarClientes() {
        List<String> resultado = new ArrayList<>();
        List<Cliente> clientes = repositorio.listarTodos();
        for (Cliente c : clientes) {
            resultado.add(c.toString());
        }
        return resultado;
    }

    /**
     * Caso de uso: Obtener la informacion consolidada de un cliente.
     * (Adaptado del metodo informacionEnviadaSubsistemas original)
     */
    public String obtenerInformacionCliente(ClienteDTO dto) {
        StringBuilder texto = new StringBuilder("----Información enviada---");
        if (registroCliente != null && mensajeriaCliente != null && configuracionCliente != null) {
            texto.append("\nRegistro: ").append(registroCliente.getId())
                 .append(" ").append(registroCliente.getNombres())
                 .append(" ").append(registroCliente.getApellidos());
            texto.append("\nMensajeria: ").append(mensajeriaCliente.mensajeEnviado());
            texto.append("\nConfiguracion: ").append(configuracionCliente.getTexto());
        }
        return texto.toString();
    }
}
