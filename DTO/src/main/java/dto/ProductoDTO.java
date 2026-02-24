package dto;

/**
 * Data Transfer Object para Producto.
 * Patrón Builder para construir instancias de forma fluida.
 * Visible para todas las capas (componente transversal).
 */
public class ProductoDTO {

    private final int    id;
    private final String nombre;
    private final double precio;
    private final int    cantidad;

    public ProductoDTO(int id, String nombre, double precio, int cantidad) {
        this.id       = id;
        this.nombre   = nombre;
        this.precio   = precio;
        this.cantidad = cantidad;
    }

    // Constructor privado para el Builder
    private ProductoDTO(Builder builder) {
        this.id       = builder.id;
        this.nombre   = builder.nombre;
        this.precio   = builder.precio;
        this.cantidad = builder.cantidad;
    }

    // ===================== BUILDER PATTERN =====================
    public static class Builder {
        private int    id;
        private String nombre;
        private double precio;
        private int    cantidad;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder precio(double precio) {
            this.precio = precio;
            return this;
        }

        public Builder cantidad(int cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public ProductoDTO build() {
            return new ProductoDTO(this);
        }
    }

    public int    getId()       { return id; }
    public String getNombre()   { return nombre; }
    public double getPrecio()   { return precio; }
    public int    getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return "ProductoDTO{id=" + id
                + ", nombre=" + nombre
                + ", precio=" + precio
                + ", cantidad=" + cantidad + "}";
    }
}
