package App2ComponenteTextoPlano;

import App2Interfaces.Documento;

public class DocumentoTexto implements Documento {
    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando Documento en la aplicación. " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo Documento. " + contenido);
    }
}
