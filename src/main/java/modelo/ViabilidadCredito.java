package modelo;

public class ViabilidadCredito {

    private Usuario usuario;
    private Credito credito;

    public ViabilidadCredito(Usuario usuario, Credito credito) {
        if (usuario == null || credito == null) {
            throw new IllegalArgumentException("Usuario y crédito no pueden ser nulos.");
        }
        this.usuario = usuario;
        this.credito = credito;
    }

    // Fórmula CMP = (Ingresos Fijos - Gastos Fijos) * 0.35
    public double calcularCMP() {
        return (usuario.getIngresos() - usuario.getGastos()) * 0.35;
    }

    // Fórmula CE = ((Gastos Fijos + Deudas) / Ingresos) * 100
    public double calcularCE() {
        double ingresos = usuario.getIngresos();
        if (ingresos == 0) return 100; // Evita división por cero
        return ((usuario.getGastos() + usuario.getDeudas()) / ingresos) * 100;
    }

    // Cuota promedio del crédito
    public double calcularCuotaPromedio() {
        int n = credito.getMetodoAmortizacion().getNumeroCuotas();
        if (n <= 0) throw new IllegalArgumentException("El número de cuotas debe ser mayor a cero.");

        double suma = 0;
        for (int t = 1; t <= n; t++) {
            suma += credito.obtenerCuota(t);
        }
        return suma / n;
    }

    // Evaluación de viabilidad según CMP
    public boolean esViablePorCMP() {
        return calcularCMP() >= calcularCuotaPromedio();
    }

    // Evaluación de viabilidad según CE (ejemplo: máximo 40%)
    public boolean esViablePorCE() {
        return calcularCE() <= 40;
    }

    // Evaluación final (puedes ajustar la lógica combinada)
    public boolean esViable() {
        return esViablePorCMP() && esViablePorCE();
    }
}