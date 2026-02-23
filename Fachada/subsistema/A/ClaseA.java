package subsistema.A;

public class ClaseA {
    private double id;
    private String nombres;
    private String apellidos;

    public void cargarInformacionTerceros(){
        System.out.println("Información enviada al sistema contable.");
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
