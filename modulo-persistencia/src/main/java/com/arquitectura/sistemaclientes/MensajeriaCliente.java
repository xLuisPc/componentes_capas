package com.arquitectura.sistemaclientes;

/**
 * Subsistema B del SistemaClientes - Envio de mensajes.
 * (Adaptado del codigo original: subsistema.B.ClaseB)
 */
public class MensajeriaCliente {

    private String destino;
    private String remitente;
    private String mensaje;

    public MensajeriaCliente(String destino, String mensaje) {
        this.destino = destino;
        this.remitente = "***miempresa***";
        this.mensaje = mensaje;
    }

    public void enviarMensaje() {
        System.out.println("Información enviada al sistema de mensajes.");
        System.out.println("\tEnviando mensaje:");
        System.out.println("\tDestino: " + destino);
        System.out.println("\tRemitente: " + remitente);
        System.out.println("\tMensaje: " + mensaje);
        System.out.println("\tMensaje enviado.");
    }

    public String mensajeEnviado() {
        return "\nDestino: " + destino + "\nRemitente: " + remitente + "\nMensaje: " + mensaje;
    }
}
