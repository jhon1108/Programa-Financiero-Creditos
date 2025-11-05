package modelo;

public class Usuario {

    private String nombre;
    private double ingresosFijos;
    private double gastosFijos;
    private double deudas;

    public Usuario(String nombre, double ingresosFijos, double gastosFijos, double deudas) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (ingresosFijos < 0 || gastosFijos < 0 || deudas < 0) {
            throw new IllegalArgumentException("Los valores financieros no pueden ser negativos.");
        }
        this.nombre = nombre;
        this.ingresosFijos = ingresosFijos;
        this.gastosFijos = gastosFijos;
        this.deudas = deudas;
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

    public double capacidadMensual() {
        return ingresosFijos - gastosFijos - deudas;
    }

    public boolean puedePagar(double cuota) {
        if (cuota < 0) {
            throw new IllegalArgumentException("La cuota no puede ser negativa.");
        }
        return capacidadMensual() >= cuota;
    }

    public String resumenFinanciero() {
        return String.format(
                "Usuario: %s\nIngresos: %.2f\nGastos: %.2f\nDeudas: %.2f\nCapacidad mensual disponible: %.2f",
                nombre, ingresosFijos, gastosFijos, deudas, capacidadMensual()
        );
    }
}