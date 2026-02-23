package patron.facade;

import subsistema.A.ClaseA;
import subsistema.B.ClaseB;
import subsistema.C.ClaseC;

public class ImplementacionFachada implements IFacade {
    private ClaseA claseA;
    private ClaseB claseB;
    private ClaseC claseC;
    
    @Override
    public void enviarInformacionSubsistemas(double id, String nombres,
                                             String apellidos, String email_detino,
                                             String texto_mensaje) {
        
        claseA=new ClaseA();
        
        claseA.setId(id);
        claseA.setNombres(nombres);
        claseA.setApellidos(apellidos);
        claseA.cargarInformacionTerceros();
        
        claseB=new ClaseB(email_detino, texto_mensaje);
        claseB.enviarMensaje();
        
        claseC=new ClaseC(id + " :"+nombres+" :"+apellidos+"\n");
        claseC.procesarInformacion();
    }
    
    @Override
    public String informacionEnviadaSubsistemas() {
        String texto;
        texto="----Información enviada---";
        if (claseA!=null && claseB!=null && claseC!=null)
        {
            texto=texto.concat("\nClaseA: "+claseA.getId() + " " + 
                              claseA.getNombres() + " " + 
                              claseA.getApellidos());
            texto=texto.concat("\nClaseB: "+claseB.mensajeEnviado());
            texto=texto.concat("\nClaseC: "+claseC.getTexto());
        }
        return texto;
    }
}
