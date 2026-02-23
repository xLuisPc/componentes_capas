package com.arquitectura.inicio;

import com.arquitectura.negocio.controlador.Controlador;
import com.arquitectura.negocio.fachada.Fachada;
import com.arquitectura.negocio.fachada.IFachada;
import com.arquitectura.persistencia.IRepositorioCliente;
import com.arquitectura.persistencia.IRepositorioDocumento;
import com.arquitectura.sistemaclientes.RepositorioClienteMySQL;
import com.arquitectura.gestordocumentos.RepositorioDocumentoH2;
import com.arquitectura.presentacion.consola.VistaConsola;
import com.arquitectura.presentacion.escritorio.VistaEscritorio;

import java.util.Scanner;

/**
 * Componente Inicio.
 * Punto de entrada de la aplicacion monolitica.
 * Aqui se realiza la INYECCION DE DEPENDENCIAS manual:
 *   - Se crean las implementaciones concretas de los repositorios.
 *   - Se inyectan en la Fachada.
 *   - Se inyecta la Fachada (como IFachada) en el Controlador.
 *   - Se inyecta el Controlador en la Vista seleccionada.
 */
public class Inicio {

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  ARQUITECTURA MONOLITICA CON CAPAS LOGICAS");
        System.out.println("=============================================");
        System.out.println();

        // === INYECCION DE DEPENDENCIAS ===

        // 1. Capa de Persistencia: crear repositorios concretos
        IRepositorioCliente repoClientes = new RepositorioClienteMySQL();    // MySQL
        IRepositorioDocumento repoDocumentos = new RepositorioDocumentoH2(); // H2

        // 2. Capa de Negocio: crear Fachada con inyeccion de repositorios
        IFachada fachada = new Fachada(repoClientes, repoDocumentos);

        // 3. Controlador: se conecta a la Fachada por la interfaz IFachada
        Controlador controlador = new Controlador(fachada);

        // 4. Capa de Presentacion: elegir la vista
        System.out.println("Seleccione la interfaz grafica:");
        System.out.println("  1. Consola");
        System.out.println("  2. Escritorio (Swing)");
        System.out.print("Opcion: ");

        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1" -> {
                System.out.println("\nIniciando Vista de Consola...\n");
                VistaConsola vistaConsola = new VistaConsola(controlador);
                vistaConsola.iniciar();
            }
            case "2" -> {
                System.out.println("\nIniciando Vista de Escritorio (Swing)...\n");
                VistaEscritorio vistaEscritorio = new VistaEscritorio(controlador);
                vistaEscritorio.iniciar();
            }
            default -> {
                System.out.println("Opcion no valida. Saliendo...");
            }
        }
    }
}
