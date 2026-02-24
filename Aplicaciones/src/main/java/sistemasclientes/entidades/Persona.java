package sistemasclientes.entidades;

import dto.PersonaDTO;

/**
 * Entidad del dominio Persona / SistemaClientes.
 * Patrón Builder para construir instancias de forma segura.
 */
public class Persona {

    private Double identificacion;
    private String nombres;
    private String apellidos;
    private int    edad;
    private String codigo;

    public Persona() {
        this.identificacion = 0.0;
        this.nombres        = "---";
        this.apellidos      = "---";
        this.edad           = 0;
        this.codigo         = "XYZ";
    }

    // Constructor privado para el Builder
    private Persona(Builder builder) {
        this.identificacion = builder.identificacion;
        this.nombres        = builder.nombres;
        this.apellidos      = builder.apellidos;
        this.edad           = builder.edad;
        this.codigo         = "XYZ" + (builder.identificacion != null
                                        ? builder.identificacion.toString() : "");
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

        public Persona build() {
            return new Persona(this);
        }
    }

    public void setDatosPersona(PersonaDTO dto) {
        this.identificacion = dto.getIdentificacion();
        this.nombres        = dto.getNombres();
        this.apellidos      = dto.getApellidos();
        this.edad           = dto.getEdad();
        this.codigo         = "XYZ" + this.identificacion.toString();
    }

    public Double getIdentificacion() { return identificacion; }
    public String getNombres()        { return nombres; }
    public String getApellidos()      { return apellidos; }
    public int    getEdad()           { return edad; }
    public String getCodigo()         { return codigo; }

    @Override
    public String toString() {
        return "Persona{id=" + identificacion
                + ", nombres=" + nombres
                + ", apellidos=" + apellidos
                + ", edad=" + edad + "}";
    }
}
