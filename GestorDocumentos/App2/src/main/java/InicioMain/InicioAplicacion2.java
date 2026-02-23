package InicioMain;

import App2Interfaces.Documento;
import Factory.FabricaDocumento;

public class InicioAplicacion2 {

    public static void main(String[] args) {
        System.out.println("=== Documento HTML ===");
        Documento docHtml = FabricaDocumento.crearDocumentoHTML("<HTML></HTML>");
        docHtml.dibujar();
        docHtml.imprimir();

        System.out.println("\n=== Documento Texto Plano ===");
        Documento docTexto = FabricaDocumento.crearDocumentoTexto("...texto...");
        docTexto.dibujar();
        docTexto.imprimir();

        System.out.println("\n=== Documento PDF (Adaptado) ===");
        Documento docPdf = FabricaDocumento.crearDocumentoPDF("...PDF...PDF...PDF...");
        docPdf.dibujar();
        docPdf.imprimir();
    }
}
