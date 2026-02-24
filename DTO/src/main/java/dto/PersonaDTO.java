package dto;

/**
 * Data Transfer Object para Persona.
 * Patrón Builder para construir instancias de forma fluida.
 * Visible para todas las capas (componente transversal).
 */
public class PersonaDTO {

    private final Double identificacion;
    private final String nombres;
    private final String apellidos;
    private final int    edad;

    public PersonaDTO(Double identificacion, String nombres, String apellidos, int edad) {
        this.identificacion = identificacion;
        this.nombres        = nombres;
        this.apellidos      = apellidos;
        this.edad           = edad;
    }

    // Constructor privado para el Builder
    private PersonaDTO(Builder builder) {
        this.identificacion = builder.identificacion;
        this.nombres        = builder.nombres;
        this.apellidos      = builder.apellidos;
        this.edad           = builder.edad;
    }

    // ===================== BUILDER PATTERN =====================
    public static class Builder {
        private Double identificacion;
        private String nombres;
        private String apellidos;
        private int    edad;

        public Builder identificacion(Double identificacion) {
            this.identificacion = identificacion;
            return this;
        }

        public Builder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public Builder edad(int edad) {
            this.edad = edad;
            return this;
        }

        public PersonaDTO build() {
            return new PersonaDTO(this);
        }
    }

    public Double getIdentificacion() { return identificacion; }
    public String getNombres()        { return nombres; }
    public String getApellidos()      { return apellidos; }
    public int    getEdad()           { return edad; }

    @Override
    public String toString() {
        return "PersonaDTO{id=" + identificacion
                + ", nombres=" + nombres
                + ", apellidos=" + apellidos
                + ", edad=" + edad + "}";
    }
}
