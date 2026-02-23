package com.arquitectura.negocio.servicio;

import com.arquitectura.dto.DocumentoDTO;
import com.arquitectura.entidades.Documento;
import com.arquitectura.gestordocumentos.*;
import com.arquitectura.persistencia.IRepositorioDocumento;

import java.util.ArrayList;
import java.util.List;

/**
 * Logica de Negocio - Casos de Uso - Servicio para GestorDocumentos.
 * Coordina la creacion, visualizacion y persistencia de documentos.
 * (Logica adaptada del codigo original: FabricaDocumento + InicioAplicacion2)
 *
 * Principio SOLID:
 *   - Single Responsibility: solo logica de negocio de documentos.
 */
public class ServicioDocumentos {

    private final IRepositorioDocumento repositorio;

    public ServicioDocumentos(IRepositorioDocumento repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Caso de uso: Crear y guardar un documento.
     * Usa Factory Method internamente para crear el componente adecuado.
     */
    public void crearDocumento(DocumentoDTO dto) {
        // Persistir en BD (H2)
        Documento entidad = new Documento(dto.getId(), dto.getTipo(), dto.getContenido());
        repositorio.guardar(entidad);

        // Crear componente visual y mostrar
        IDocumentoComponente componente = crearComponente(dto.getTipo(), dto.getContenido());
        if (componente != null) {
            System.out.println("=== Documento " + dto.getTipo() + " creado ===");
            componente.dibujar();
        }
    }

    /**
     * Caso de uso: Listar todos los documentos guardados.
     */
    public List<String> listarDocumentos() {
        List<String> resultado = new ArrayList<>();
        List<Documento> documentos = repositorio.listarTodos();
        for (Documento d : documentos) {
            resultado.add(d.toString());
        }
        return resultado;
    }

    /**
     * Caso de uso: Visualizar e imprimir un documento.
     */
    public void visualizarDocumento(DocumentoDTO dto) {
        IDocumentoComponente componente = crearComponente(dto.getTipo(), dto.getContenido());
        if (componente != null) {
            componente.dibujar();
            componente.imprimir();
        } else {
            System.out.println("Tipo de documento no soportado: " + dto.getTipo());
        }
    }

    /**
     * Factory Method interno: crea el componente adecuado segun el tipo.
     * (Adaptado del codigo original: FabricaDocumento)
     */
    private IDocumentoComponente crearComponente(String tipo, String contenido) {
        IDocumentoComponente componente = switch (tipo.toUpperCase()) {
            case "HTML" -> new DocumentoHtml();
            case "TEXTO" -> new DocumentoTexto();
            case "PDF" -> new DocumentoPdfAdaptado();
            default -> null;
        };
        if (componente != null) {
            componente.setContenido(contenido);
        }
        return componente;
    }
}
