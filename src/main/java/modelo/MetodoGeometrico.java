package modelo;

public class MetodoGeometrico extends MetodoAmortizacion {

    private double cuotaInicial;
    private double factorCrecimiento;

    public MetodoGeometrico(double capital, double tasaInteres, int numeroCuotas,
                            double factorCrecimiento) {
        super(capital, tasaInteres, numeroCuotas);
        this.factorCrecimiento = factorCrecimiento;
        this.cuotaInicial = capital * (1 - factorCrecimiento) / (1 - Math.pow(factorCrecimiento, numeroCuotas));
    }

    @Override
    public double calcular_a_t(int periodo) {
        // Cuota total mensual = amortización + interés
        double capitalAmortizado = calcular_A_t(periodo);
        double interes = calcular_I_t(periodo);
        return redondear(capitalAmortizado + interes);
    }

    @Override
    public double calcular_I_t(int periodo) {
        // Interés = saldo anterior * tasa
        double saldoAnterior = (periodo == 1) ? capital : calcular_S_t(periodo - 1);
        return redondear(saldoAnterior * tasaInteres);
    }

    @Override
    public double calcular_A_t(int periodo) {
        // Amortización del capital geométrica decreciente
        return redondear(cuotaInicial * Math.pow(factorCrecimiento, periodo - 1));
    }

    @Override
    public double calcular_S_t(int periodo) {
        // Saldo restante = capital - suma de todas las amortizaciones anteriores
        double saldo = capital;
        for (int t = 1; t <= periodo; t++) {
            saldo -= calcular_A_t(t);
            if (saldo < 0) saldo = 0;
        }
        return redondear(saldo);
    }

    // ===== DERIVADAS DISCRETAS =====

    @Override
    public double derivada_a_t(int periodo) {
        if (periodo <= 1) return 0;
        return redondear(calcular_a_t(periodo) - calcular_a_t(periodo - 1));
    }

    @Override
    public double derivada_A_t(int periodo) {
        if (periodo <= 1) return 0;
        return redondear(calcular_A_t(periodo) - calcular_A_t(periodo - 1));
    }

    @Override
    public double derivada_I_t(int periodo) {
        if (periodo <= 1) return 0;
        return redondear(calcular_I_t(periodo) - calcular_I_t(periodo - 1));
    }

    @Override
    public double derivada_S_t(int periodo) {
        if (periodo <= 1) return 0;
        return redondear(calcular_S_t(periodo) - calcular_S_t(periodo - 1));
    }

    // ===== DERIVADAS PARCIALES =====

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        double delta = 1e-3;
        double orig = capital;
        capital = orig + delta;
        double plus = calcular_S_t(periodo);
        capital = orig - delta;
        double minus = calcular_S_t(periodo);
        capital = orig;
        return (plus - minus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        double delta = 1e-4;
        double orig = tasaInteres;
        tasaInteres = orig + delta;
        double plus = calcular_S_t(periodo);
        tasaInteres = orig - delta;
        double minus = calcular_S_t(periodo);
        tasaInteres = orig;
        return (plus - minus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        if (periodo <= 1) return 0;
        return calcular_S_t(periodo) - calcular_S_t(periodo - 1);
    }

    // ===== Utilidad =====
    private double redondear(double x) {
        return Math.round(x * 100.0) / 100.0;
    }
}

