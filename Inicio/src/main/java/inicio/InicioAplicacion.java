package inicio;

import consola.VistaConsola;
import controlador.Controlador;
import escritorio.VistaEscritorio;
import fabrica.FabricaDTO;
import fachada.Fachada;
import fachada.IFachada;
import logica.LogicaNegocio;

import java.util.Scanner;

/**
 * Punto de entrada de la aplicación ComponentesConCapas.
 * Responsabilidad: instanciar todos los componentes e inyectar dependencias.
 * El main es lo más simple posible — solo conecta las piezas.
 *
 * Inyección de Dependencias manual (sin framework).
 */
public class InicioAplicacion {

    public static void main(String[] args) {

        // ── Componentes transversales ────────────────────────────
        FabricaDTO fabricaDTO = FabricaDTO.getInstance();

        // ── Capa CapaMediana ─────────────────────────────────────
        IFachada      fachada       = new Fachada();
        LogicaNegocio logicaNegocio = new LogicaNegocio();
        Controlador   controlador   = new Controlador(fachada, logicaNegocio);

        // ── Elegir interfaz ──────────────────────────────────────
        Scanner scanner = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       ComponentesConCapas            ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  Seleccione la interfaz gráfica:     ║");
        System.out.println("║    1. Consola                        ║");
        System.out.println("║    2. Escritorio (Swing)             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Opción: ");

        String opcion = scanner.nextLine().trim();
        scanner.close();

        if ("2".equals(opcion)) {
            // ── Vista Escritorio ─────────────────────────────────
            VistaEscritorio escritorio = new VistaEscritorio(controlador, fabricaDTO);
            escritorio.mostrar();
        } else {
            // ── Vista Consola (opción por defecto) ───────────────
            VistaConsola consola = new VistaConsola(controlador, fabricaDTO);
            consola.iniciar();
        }
    }
}
