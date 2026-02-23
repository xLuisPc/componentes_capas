package App2ComponenteHTML;

import App2Interfaces.Documento;

public class DocumentoHtml implements Documento {
    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando DocumentoHTML en la aplicación. " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo DocumentoHTML. " + contenido);
    }
}
