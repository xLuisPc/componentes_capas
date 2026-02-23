package com.arquitectura.presentacion.consola;

import com.arquitectura.negocio.controlador.Controlador;

import java.util.List;
import java.util.Scanner;

/**
 * Componente VistaConsola.
 * Interfaz grafica de usuario por consola (IGU texto).
 * Accede a los servicios de las capas a traves del Controlador.
 */
public class VistaConsola {

    private final Controlador controlador;
    private final Scanner scanner;

    public VistaConsola(Controlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el menu principal de la consola.
     */
    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1" -> registrarCliente();
                case "2" -> listarClientes();
                case "3" -> crearDocumento();
                case "4" -> listarDocumentos();
                case "5" -> visualizarDocumento();
                case "0" -> {
                    System.out.println("Saliendo de la vista de consola...");
                    salir = true;
                }
                default -> System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("   MONOLITO CON CAPAS - VISTA CONSOLA");
        System.out.println("========================================");
        System.out.println(" [SISTEMA CLIENTES - MySQL]");
        System.out.println("  1. Registrar cliente");
        System.out.println("  2. Listar clientes");
        System.out.println(" [GESTOR DOCUMENTOS - H2]");
        System.out.println("  3. Crear documento");
        System.out.println("  4. Listar documentos");
        System.out.println("  5. Visualizar documento");
        System.out.println("----------------------------------------");
        System.out.println("  0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }

    private void registrarCliente() {
        System.out.println("\n--- Registrar Cliente ---");
        System.out.print("ID: ");
        double id = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine().trim();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        String mensaje = "Bienvenido a ***miempresa***";

        controlador.registrarCliente(id, nombres, apellidos, email, mensaje);

        System.out.println("\nInformacion del cliente registrado:");
        System.out.println(controlador.obtenerInfoCliente(id, nombres, apellidos, email, mensaje));
    }

    private void listarClientes() {
        System.out.println("\n--- Clientes Registrados ---");
        List<String> clientes = controlador.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private void crearDocumento() {
        System.out.println("\n--- Crear Documento ---");
        System.out.print("Tipo (HTML, TEXTO, PDF): ");
        String tipo = scanner.nextLine().trim();
        System.out.print("Contenido: ");
        String contenido = scanner.nextLine().trim();

        controlador.crearDocumento(tipo, contenido);
    }

    private void listarDocumentos() {
        System.out.println("\n--- Documentos Guardados ---");
        List<String> documentos = controlador.listarDocumentos();
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos guardados.");
        } else {
            documentos.forEach(System.out::println);
        }
    }

    private void visualizarDocumento() {
        System.out.println("\n--- Visualizar Documento ---");
        System.out.print("Tipo (HTML, TEXTO, PDF): ");
        String tipo = scanner.nextLine().trim();
        System.out.print("Contenido: ");
        String contenido = scanner.nextLine().trim();

        controlador.visualizarDocumento(tipo, contenido);
    }
}
