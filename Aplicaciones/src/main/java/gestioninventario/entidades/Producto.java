package gestioninventario.entidades;

/**
 * Entidad del dominio GestionInventario.
 * Patrón Builder para construir instancias de forma segura.
 */
public class Producto {

    private int    id;
    private String nombre;
    private double precio;
    private int    cantidad;

    public Producto() { }

    // Constructor privado para el Builder
    private Producto(Builder builder) {
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

        public Producto build() {
            return new Producto(this);
        }
    }

    public int    getId()       { return id; }
    public String getNombre()   { return nombre; }
    public double getPrecio()   { return precio; }
    public int    getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return "Producto{id=" + id
                + ", nombre=" + nombre
                + ", precio=" + precio
                + ", cantidad=" + cantidad + "}";
    }
}
