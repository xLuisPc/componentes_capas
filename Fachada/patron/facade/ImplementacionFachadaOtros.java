package patron.facade;

public class ImplementacionFachadaOtros implements IFacade {

    public ImplementacionFachadaOtros() { }

    public ImplementacionFachadaOtros(int i, String juan, String martinez,
                                      String jmartinezgmailcom, String bienvenido_a_miempresa) {
        System.out.println("***");
    }

    @Override
    public void enviarInformacionSubsistemas(double id, String nombres, String apellidos,
                                             String email_detino, String texto_mensaje) {
        System.out.println("");
    }

    @Override
    public String informacionEnviadaSubsistemas() {
        return("");
    }
}
