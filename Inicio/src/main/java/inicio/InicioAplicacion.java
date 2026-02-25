package inicio;

import consola.VistaConsola;
import controlador.Controlador;
import escritorio.VistaEscritorio;
import fabrica.FabricaDTO;

import java.util.Scanner;

/**
 * Punto de entrada de la aplicación ComponentesConCapas.
 *
 * RESPETA EL FLUJO DE CAPAS: Inicio → Presentación → CapaMediana
 *
 * Responsabilidad:
 *   - Solo actúa como bootstrap (arranque) de la aplicación.
 *   - NO instancia componentes de CapaMediana (delega a FabricaDTO).
 *   - SÍ instancia directamente Presentación (vistas), ya que es la interfaz de usuario.
 *   - Comunica las decisiones del usuario a las vistas.
 *
 * Flujo de control:
 *   1. Inicio → obtiene FabricaDTO (singleton).
 *   2. Inicio → pide a la fábrica que cree el Controlador (CapaMediana).
 *   3. Inicio → crea directamente la Vista elegida (Presentación) con el Controlador.
 *   4. Inicio → inicia la Vista (que toma el control desde aquí).
 *
 * Inyección de Dependencias: manual a través de FabricaDTO para CapaMediana.
 * Principios SOLID: Dependency Inversion (depende de FabricaDTO para CapaMediana).
 */
public class InicioAplicacion {

    public static void main(String[] args) {

        // ── Obtener instancia de la fábrica (Singleton) ────────────────
        // FabricaDTO es transversal y conocida por todas las capas.
        FabricaDTO fabrica = FabricaDTO.getInstance();

        // ── Elegir interfaz de usuario ────────────────────────────────
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

        // ── PASO 1: Crear el Controlador (CapaMediana) vía fábrica ─────
        // La fábrica se encarga de inyectar sus dependencias internas.
        Controlador controlador = fabrica.crearControlador();

        // ── PASO 2: Crear e iniciar la Vista (Presentación) directamente ─
        // Inicio es responsable de instanciar la Vista elegida.
        if ("2".equals(opcion)) {
            // ── Vista Escritorio (Swing) ──────────────────────────────
            VistaEscritorio escritorio = new VistaEscritorio(controlador, fabrica);
            escritorio.mostrar();
        } else {
            // ── Vista Consola (opción por defecto) ────────────────────
            VistaConsola consola = new VistaConsola(controlador, fabrica);
            consola.iniciar();
        }

        // Fin de la aplicación cuando el usuario cierra una vista.
    }
}
