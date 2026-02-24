package dto;

/**
 * Data Transfer Object para Documento.
 * Patrón Builder para construir instancias de forma fluida.
 * Visible para todas las capas (componente transversal).
 * Tipos soportados: HTML, TEXTO, PDF
 */
public class DocumentoDTO {

    private final String tipo;
    private final String contenido;

    public DocumentoDTO(String tipo, String contenido) {
        this.tipo      = tipo;
        this.contenido = contenido;
    }

    // Constructor privado para el Builder
    private DocumentoDTO(Builder builder) {
        this.tipo      = builder.tipo;
        this.contenido = builder.contenido;
    }

    // ===================== BUILDER PATTERN =====================
    public static class Builder {
        private String tipo;
        private String contenido;

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder contenido(String contenido) {
            this.contenido = contenido;
            return this;
        }

        public DocumentoDTO build() {
            return new DocumentoDTO(this);
        }
    }

    public String getTipo()      { return tipo; }
    public String getContenido() { return contenido; }

    @Override
    public String toString() {
        return "DocumentoDTO{tipo=" + tipo + ", contenido=" + contenido + "}";
    }
}
