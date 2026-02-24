package consola;

import controlador.Controlador;
import dto.DocumentoDTO;
import dto.PersonaDTO;
import dto.ProductoDTO;
import fabrica.FabricaDTO;

import java.util.List;
import java.util.Scanner;

/**
 * Vista de consola (capa de Presentación).
 * Permite al usuario elegir el dominio y la operación a ejecutar.
 * Inyección de Dependencias: recibe el Controlador y FabricaDTO por constructor.
 */
public class VistaConsola {

    private final Controlador controlador;
    private final FabricaDTO  fabricaDTO;
    private final Scanner     scanner;

    public VistaConsola(Controlador controlador, FabricaDTO fabricaDTO) {
        this.controlador = controlador;
        this.fabricaDTO  = fabricaDTO;
        this.scanner     = new Scanner(System.in);
    }

    /** Punto de entrada de la vista consola */
    public void iniciar() {
        boolean continuar = true;
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   ComponentesConCapas — Consola      ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (continuar) {
            System.out.println("\n═══ MENÚ PRINCIPAL ═══");
            System.out.println("1. Sistema de Clientes");
            System.out.println("2. Gestor de Documentos");
            System.out.println("3. Gestión de Inventario");
            System.out.println("4. Salir");
            System.out.print("Seleccione dominio: ");

            switch (scanner.nextLine().trim()) {
                case "1": menuClientes();    break;
                case "2": menuDocumentos();  break;
                case "3": menuInventario();  break;
                case "4": continuar = false; System.out.println("¡Hasta luego!"); break;
                default:  System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    // ════════════════════ SISTEMA CLIENTES ════════════════════
    private void menuClientes() {
        System.out.println("\n─── Sistema de Clientes ───");
        System.out.println("1. Crear persona");
        System.out.println("2. Listar personas");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        switch (scanner.nextLine().trim()) {
            case "1": crearPersona();  break;
            case "2": listarPersonas(); break;
        }
    }

    private void crearPersona() {
        System.out.println("\n--- Registrar nueva persona ---");
        System.out.print("Identificación: ");
        double id = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine().trim();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine().trim();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine().trim());

        PersonaDTO dto = fabricaDTO.crearPersonaDTO(id, nombres, apellidos, edad);
        boolean ok = controlador.crearPersona(dto);
        System.out.println(ok ? "✔ Persona registrada." : "✘ Error al registrar.");
    }

    private void listarPersonas() {
        List<PersonaDTO> lista = controlador.listarPersonas();
        System.out.println("\n─── Personas registradas (" + lista.size() + ") ───");
        lista.forEach(p -> System.out.println("  → " + p));
    }

    // ════════════════════ GESTOR DOCUMENTOS ════════════════════
    private void menuDocumentos() {
        System.out.println("\n─── Gestor de Documentos ───");
        System.out.println("1. Crear documento HTML");
        System.out.println("2. Crear documento Texto");
        System.out.println("3. Crear documento PDF");
        System.out.println("4. Listar documentos");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        String op = scanner.nextLine().trim();
        switch (op) {
            case "1": crearDocumento("HTML");  break;
            case "2": crearDocumento("TEXTO"); break;
            case "3": crearDocumento("PDF");   break;
            case "4": listarDocumentos();       break;
        }
    }

    private void crearDocumento(String tipo) {
        System.out.print("Ingrese el contenido (" + tipo + "): ");
        String contenido = scanner.nextLine().trim();
        DocumentoDTO dto = fabricaDTO.crearDocumentoDTO(tipo, contenido);
        boolean ok = controlador.crearDocumento(dto);
        System.out.println(ok ? "✔ Documento creado." : "✘ Error al crear documento.");
    }

    private void listarDocumentos() {
        List<DocumentoDTO> lista = controlador.listarDocumentos();
        System.out.println("\n─── Documentos registrados (" + lista.size() + ") ───");
        lista.forEach(d -> System.out.println("  [" + d.getTipo() + "] " + d.getContenido()));
    }

    // ════════════════════ GESTIÓN INVENTARIO ════════════════════
    private void menuInventario() {
        System.out.println("\n─── Gestión de Inventario ───");
        System.out.println("1. Crear producto");
        System.out.println("2. Listar productos");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        switch (scanner.nextLine().trim()) {
            case "1": crearProducto();  break;
            case "2": listarProductos(); break;
        }
    }

    private void crearProducto() {
        System.out.println("\n--- Registrar nuevo producto ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine().trim());

        ProductoDTO dto = fabricaDTO.crearProductoDTO(0, nombre, precio, cantidad);
        boolean ok = controlador.crearProducto(dto);
        System.out.println(ok ? "✔ Producto registrado." : "✘ Error al registrar.");
    }

    private void listarProductos() {
        List<ProductoDTO> lista = controlador.listarProductos();
        System.out.println("\n─── Productos en inventario (" + lista.size() + ") ───");
        lista.forEach(p -> System.out.println("  → " + p));
    }
}
