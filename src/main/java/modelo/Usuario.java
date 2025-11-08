package modelo;

public class Usuario {

    private String nombre;
    private double ingresos;
    private double gastos;
    private double deudas;
    private ViabilidadFinanciera viabilidad;

    public Usuario(String nombre, double ingresos, double gastos, double deudas) {
        this.nombre = nombre;
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.deudas = deudas;

        // Genera viabilidad al crear el usuario
        this.viabilidad = new ViabilidadFinanciera(this);
    }

    public String getNombre() { return nombre; }
    public double getIngresos() { return ingresos; }
    public double getGastos() { return gastos; }
    public double getDeudas() { return deudas; }
    public ViabilidadFinanciera getViabilidad() { return viabilidad; }

    @Override
    public String toString() {
        return String.format(
                "Usuario: %s\nIngresos: %.2f\nGastos: %.2f\nDeudas: %.2f\nCE: %.2f%%\nCMP: %.2f\nRango: %s",
                nombre, ingresos, gastos, deudas,
                viabilidad.getCe(),
                viabilidad.getCmp(),
                viabilidad.getRango()
        );
    }
}
