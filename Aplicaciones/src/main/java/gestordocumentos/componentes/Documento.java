package gestordocumentos.componentes;

/**
 * Interfaz del dominio Documento (copiada de App2).
 * Define el contrato para todos los tipos de documento.
 */
public interface Documento {
    void setContenido(String contenido);
    void dibujar();
    void imprimir();
}
