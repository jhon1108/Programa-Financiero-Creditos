package modelo;

public class MetodoAleman extends MetodoAmortizacion {

    public MetodoAleman(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    // ===================== MÉTODOS PRINCIPALES =====================

    @Override
    public double calcular_a_t(int periodo) {
        double r = tasaInteres;
        int n = numeroCuotas;

        // Amortización fija (constante)
        double A_t = capital / n;

        // Interés decreciente: depende del saldo inicial pendiente
        double I_t = capital * r * (1 - ((double) (periodo - 1) / n));

        // Cuota total: amortización + interés
        double a_t = A_t + I_t;

        return Math.round(a_t * 100.0) / 100.0;
    }

    @Override
    public double calcular_A_t(int periodo) {
        // Amortización fija
        double A_t = capital / numeroCuotas;
        return Math.round(A_t * 100.0) / 100.0;
    }

    @Override
    public double calcular_I_t(int periodo) {
        double r = tasaInteres;
        int n = numeroCuotas;

        // Interés decreciente lineal
        double I_t = capital * r * (1 - ((double) (periodo - 1) / n));

        return Math.round(I_t * 100.0) / 100.0;
    }

    @Override
    public double calcular_S_t(int periodo) {
        int n = numeroCuotas;

        // Saldo después de t pagos
        double S_t = capital * (1 - ((double) periodo / n));

        if (periodo >= n) S_t = 0;

        return Math.max(0, Math.round(S_t * 100.0) / 100.0);
    }

    // ===================== DERIVADAS ENTRE PERIODOS (SUAVIZADAS) =====================

    @Override
    public double derivada_a_t(int periodo) {
        if (periodo == 1)
            return Math.round((calcular_a_t(2) - calcular_a_t(1)) * 100.0) / 100.0;
        return Math.round((calcular_a_t(periodo) - calcular_a_t(periodo - 1)) * 100.0) / 100.0;
    }

    @Override
    public double derivada_A_t(int periodo) {
        // Amortización constante ⇒ derivada = 0
        return 0.0;
    }

    @Override
    public double derivada_I_t(int periodo) {
        if (periodo == 1)
            return Math.round((calcular_I_t(2) - calcular_I_t(1)) * 100.0) / 100.0;
        return Math.round((calcular_I_t(periodo) - calcular_I_t(periodo - 1)) * 100.0) / 100.0;
    }

    @Override
    public double derivada_S_t(int periodo) {
        if (periodo == 1)
            return Math.round((calcular_S_t(2) - calcular_S_t(1)) * 100.0) / 100.0;
        return Math.round((calcular_S_t(periodo) - calcular_S_t(periodo - 1)) * 100.0) / 100.0;
    }

    // ===================== DERIVADAS PERSONALIZADAS =====================

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        // El saldo disminuye linealmente con el tiempo
        return 1 - ((double) periodo / numeroCuotas);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        // La tasa no cambia el saldo directamente en el método alemán (solo el interés)
        return 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        // La pendiente del saldo es negativa y constante
        return -capital / numeroCuotas;
    }
}
