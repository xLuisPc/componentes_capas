package com.arquitectura.sistemaclientes;

/**
 * Subsistema C del SistemaClientes - Configuracion y procesamiento.
 * (Adaptado del codigo original: subsistema.C.ClaseC)
 */
public class ConfiguracionCliente {

    private String texto;

    public ConfiguracionCliente(String texto) {
        this.texto = texto;
    }

    public void procesarInformacion() {
        System.out.println("Información procesada para configuración.");
        System.out.println("\tCorreo electrónico y demás configuraciones creadas: " + texto.toUpperCase());
    }

    public String getTexto() {
        return texto.toUpperCase();
    }
}
