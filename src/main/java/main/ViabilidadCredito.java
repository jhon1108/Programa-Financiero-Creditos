package main;

public class ViabilidadCredito {

    private Usuario usuario;
    private Credito credito;

    public ViabilidadCredito(Usuario usuario, Credito credito) {
        this.usuario = usuario;
        this.credito = credito;
    }

    // Evalúa si el usuario puede pagar la cuota promedio
    public boolean esViable() {
        double cuotaPromedio = calcularCuotaPromedio();
        return usuario.puedePagar(cuotaPromedio);
    }

    // Calcula la cuota promedio del crédito
    public double calcularCuotaPromedio() {
        double suma = 0;
        int n = credito.getMetodoAmortizacion().getNumeroCuotas();
        for (int t = 1; t <= n; t++) {
            suma += credito.obtenerCuota(t);
        }
        return suma / n;
    }

    // Genera un informe textual con los datos clave
    public String generarInforme() {
        double capacidad = usuario.capacidadMensual();
        double cuota = calcularCuotaPromedio();
        String estado = esViable() ? "✅ Viable" : "❌ No viable";

        return String.format(
                " Informe de Viabilidad\n" +
                        "Usuario: %s\n" +
                        "Ingresos: %.2f\n" +
                        "Gastos: %.2f\n" +
                        "Deudas: %.2f\n" +
                        "Capacidad mensual disponible: %.2f\n" +
                        "Cuota promedio del crédito: %.2f\n" +
                        "Estado: %s",
                usuario.getNombre(),
                usuario.getIngresos(),
                usuario.getGastos(),
                usuario.getDeudas(),
                capacidad,
                cuota,
                estado
        );
    }
}