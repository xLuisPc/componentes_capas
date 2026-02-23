package patron.facade;

public interface IFacade {
    public void enviarInformacionSubsistemas( double id,String nombres,
                                              String apellidos,
                                              String email_detino,
                                              String texto_mensaje);
    
    public String informacionEnviadaSubsistemas();
}
