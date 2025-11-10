package modelo;

public class MetodoFrances extends MetodoAmortizacion {

    public MetodoFrances(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    // ===================== MÉTODOS PRINCIPALES =====================

    @Override
    public double calcular_a_t(int periodo) {
        double r = tasaInteres;
        int n = numeroCuotas;

        double a = capital * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        return Math.round(a * 100.0) / 100.0;
    }

    @Override
    public double calcular_A_t(int periodo) {
        double r = tasaInteres;
        double a = calcular_a_t(periodo);

        double saldoAnterior = capital * Math.pow(1 + r, periodo - 1)
                - a * (Math.pow(1 + r, periodo - 1) - 1) / r;

        double interes = saldoAnterior * r;
        double amortizacion = a - interes;

        return Math.round(amortizacion * 100.0) / 100.0;
    }

    @Override
    public double calcular_I_t(int periodo) {
        double r = tasaInteres;
        double a = calcular_a_t(periodo);

        double saldoAnterior = capital * Math.pow(1 + r, periodo - 1)
                - a * (Math.pow(1 + r, periodo - 1) - 1) / r;

        double interes = saldoAnterior * r;
        return Math.round(interes * 100.0) / 100.0;
    }

    @Override
    public double calcular_S_t(int periodo) {
        double r = tasaInteres;
        int n = numeroCuotas;

        double a = capital * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

        double saldo = capital * Math.pow(1 + r, periodo)
                - a * (Math.pow(1 + r, periodo) - 1) / r;

        return Math.max(0, Math.round(saldo * 100.0) / 100.0);
    }

    // ===================== DERIVADAS ENTRE PERIODOS (SUAVIZADAS) =====================

    @Override
    public double derivada_a_t(int periodo) {
        // Cuota constante → derivada = 0
        return 0;
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
    public double derivadaSaldoRespectoTasa(int periodo) {
        double r = this.getTasaInteres();
        double t = (double) periodo;

        if (r == 0)
            return -t; // límite cuando r → 0

        return (1.0 / r) * (1.0 - Math.exp(r * t));
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        return (-calcular_a_t(1));
    }

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        double r = this.getTasaInteres();
        double t = (double) periodo;

        return Math.exp(r * t);
    }
}
