package modelo;

public class Usuario {

    private String nombre;
    private double ingresosFijos;
    private double gastosFijos;
    private double deudas;
    ViabilidadFinanciera viabilidadFinanciera;

    public Usuario(String nombre, double ingresosFijos, double gastosFijos, double deudas) {
        this.nombre = nombre;
        this.ingresosFijos = ingresosFijos;
        this.gastosFijos = gastosFijos;
        this.deudas = deudas;
        this.viabilidadFinanciera= new ViabilidadFinanciera(this);

    }

    public String getNombre() {
        return nombre;
    }

    public double getIngresos() {
        return ingresosFijos;
    }

    public double getGastos() {
        return gastosFijos;
    }

    public double getDeudas() {
        return deudas;
    }

    public ViabilidadFinanciera getViabilidadFinanciera() {
        return viabilidadFinanciera;
    }
}