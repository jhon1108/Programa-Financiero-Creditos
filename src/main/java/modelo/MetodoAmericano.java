package modelo;

public class MetodoAmericano extends MetodoAmortizacion {

    public MetodoAmericano(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    // ===================== MÉTODOS PRINCIPALES =====================

    @Override
    public double calcular_a_t(int periodo) {
        double r = tasaInteres;
        int n = numeroCuotas;

        double a_t;
        // En los primeros n - 1 periodos: solo se pagan intereses
        if (periodo < n) {
            a_t = capital * r;
        } else {
            // En el último periodo: se pagan intereses + capital completo
            a_t = capital * r + capital;
        }

        return Math.round(a_t * 100.0) / 100.0;
    }

    @Override
    public double calcular_A_t(int periodo) {
        int n = numeroCuotas;
        double A_t;

        // No hay amortización de capital hasta el último periodo
        if (periodo < n) {
            A_t = 0;
        } else {
            A_t = capital;
        }

        return Math.round(A_t * 100.0) / 100.0;
    }

    @Override
    public double calcular_I_t(int periodo) {
        double r = tasaInteres;
        double I_t = capital * r; // interés constante
        return Math.round(I_t * 100.0) / 100.0;
    }

    @Override
    public double calcular_S_t(int periodo) {
        int n = numeroCuotas;
        double S_t;

        // El saldo permanece igual hasta el último periodo
        if (periodo < n) {
            S_t = capital;
        } else {
            S_t = 0;
        }

        return Math.round(S_t * 100.0) / 100.0;
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
        if (periodo == 1)
            return Math.round((calcular_A_t(2) - calcular_A_t(1)) * 100.0) / 100.0;
        return Math.round((calcular_A_t(periodo) - calcular_A_t(periodo - 1)) * 100.0) / 100.0;
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
        int n = numeroCuotas;
        // Hasta el último periodo, el saldo depende completamente del capital
        return (periodo < n) ? 1.0 : 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        // En el método americano, la tasa no afecta al saldo (solo al pago de intereses)
        return 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        // El saldo solo cambia en el último pago
        if (periodo < numeroCuotas) return 0.0;
        return -capital;
    }
}
