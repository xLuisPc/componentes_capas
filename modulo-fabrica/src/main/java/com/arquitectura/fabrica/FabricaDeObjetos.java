package com.arquitectura.fabrica;

import com.arquitectura.dto.ClienteDTO;
import com.arquitectura.dto.DocumentoDTO;

/**
 * Fabrica de Objetos - Componente transversal visible para todas las capas.
 *
 * Patrones aplicados:
 *   - Singleton: unica instancia global (getInstance).
 *   - Factory Method: metodos de creacion para DTOs de ambos dominios.
 *
 * Principios SOLID:
 *   - Single Responsibility: solo se encarga de crear objetos.
 *   - Open/Closed: se pueden agregar nuevos metodos de creacion sin modificar los existentes.
 *   - Dependency Inversion: retorna interfaces/DTOs, no implementaciones concretas.
 */
public class FabricaDeObjetos {

    // --- Singleton ---
    private static FabricaDeObjetos instancia;

    private FabricaDeObjetos() {
        // Constructor privado para Singleton
    }

    public static FabricaDeObjetos getInstance() {
        if (instancia == null) {
            synchronized (FabricaDeObjetos.class) {
                if (instancia == null) {
                    instancia = new FabricaDeObjetos();
                }
            }
        }
        return instancia;
    }

    // --- Factory Method: Clientes ---

    public ClienteDTO crearClienteDTO(double id, String nombres, String apellidos,
                                       String email, String mensajeBienvenida) {
        return new ClienteDTO(id, nombres, apellidos, email, mensajeBienvenida);
    }

    // --- Factory Method: Documentos ---

    public DocumentoDTO crearDocumentoDTO(long id, String tipo, String contenido) {
        return new DocumentoDTO(id, tipo, contenido);
    }

    // --- Builder para ClienteDTO ---

    public ClienteDTOBuilder builderClienteDTO() {
        return new ClienteDTOBuilder();
    }

    // --- Builder para DocumentoDTO ---

    public DocumentoDTOBuilder builderDocumentoDTO() {
        return new DocumentoDTOBuilder();
    }

    /**
     * Builder para construir ClienteDTO paso a paso.
     * Patron Builder: permite construir objetos complejos de forma legible.
     */
    public static class ClienteDTOBuilder {
        private double id;
        private String nombres;
        private String apellidos;
        private String email;
        private String mensajeBienvenida;

        public ClienteDTOBuilder conId(double id) {
            this.id = id;
            return this;
        }

        public ClienteDTOBuilder conNombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public ClienteDTOBuilder conApellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public ClienteDTOBuilder conEmail(String email) {
            this.email = email;
            return this;
        }

        public ClienteDTOBuilder conMensajeBienvenida(String mensaje) {
            this.mensajeBienvenida = mensaje;
            return this;
        }

        public ClienteDTO build() {
            return new ClienteDTO(id, nombres, apellidos, email, mensajeBienvenida);
        }
    }

    /**
     * Builder para construir DocumentoDTO paso a paso.
     * Patron Builder: permite construir objetos complejos de forma legible.
     */
    public static class DocumentoDTOBuilder {
        private long id;
        private String tipo;
        private String contenido;

        public DocumentoDTOBuilder conId(long id) {
            this.id = id;
            return this;
        }

        public DocumentoDTOBuilder conTipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public DocumentoDTOBuilder conContenido(String contenido) {
            this.contenido = contenido;
            return this;
        }

        public DocumentoDTO build() {
            return new DocumentoDTO(id, tipo, contenido);
        }
    }
}
