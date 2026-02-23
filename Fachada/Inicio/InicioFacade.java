package Inicio;

import patron.facade.IFacade;
import patron.facade.ImplementacionFachada;
import patron.facade.ImplementacionFachadaOtros;

public class InicioFacade {
    public static void main(String d[]){
        IFacade fachada=new ImplementacionFachada();

        fachada.enviarInformacionSubsistemas(1, "Juan", "Martinez",
                                             "jmartinez@gmail.com",
                                             "Bienvenido a ***miempresa***");

        System.out.println("Informacion del nuevo usuario: " +
                          fachada.informacionEnviadaSubsistemas());
        System.out.println("\n");

        fachada.enviarInformacionSubsistemas(2, "Andres", "Rojas",
                                             "arojas@gmail.com",
                                             "Bienvenido a ***miempresa***");
        System.out.println("Informacion del nuevo usuario: " +
                          fachada.informacionEnviadaSubsistemas());

        System.out.println("***Cambio de implementación***");
        fachada=new ImplementacionFachadaOtros(1, "Juan", "Martinez",
                                                "jmartinez@gmail.com",
                                                "Bienvenido a ***miempresa***");
        System.out.println("Informacion del nuevo usuario: " +
                          fachada.informacionEnviadaSubsistemas());
    }
}
