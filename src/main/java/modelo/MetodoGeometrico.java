package modelo;

public class MetodoGeometrico extends MetodoAmortizacion {

    private double cuotaInicial;
    private double factorCrecimiento;

    public MetodoGeometrico(double capital, double tasaInteres, int numeroCuotas,
                            double cuotaInicial, double factorCrecimiento) {
        super(capital, tasaInteres, numeroCuotas);
        this.cuotaInicial = cuotaInicial;
        this.factorCrecimiento = factorCrecimiento;
    }

    @Override
    public double calcularCuota(int periodo) {
        return cuotaInicial * Math.pow(factorCrecimiento, periodo - 1);
    }

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        return tasaInteres * saldoAnterior;
    }

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        return cuota - interes;
    }

    @Override
    public double calcularSaldo(int periodo) {
        double saldo = capital;
        for (int t = 1; t <= periodo; t++) {
            double cuota = calcularCuota(t);
            double interes = calcularInteres(t, saldo);
            double amort = cuota - interes;
            if (amort < 0) amort = 0;

            saldo -= amort;
            if (saldo < 0) saldo = 0;
        }
        return saldo;
    }

    @Override
    public double calcular_a_t(int periodo) {
        double cuota = calcularCuota(periodo);
        double interes = calcular_I_t(periodo);
        return cuota - interes;
    }

    @Override
    public double calcular_A_t(int periodo) {
        double sum = 0;
        for (int t = 1; t <= periodo; t++) sum += calcular_a_t(t);
        return sum;
    }

    @Override
    public double calcular_I_t(int periodo) {
        return calcularInteres(periodo, calcularSaldo(periodo - 1));
    }

    @Override
    public double calcular_S_t(int periodo) {
        return calcularSaldo(periodo);
    }



    @Override
    public double derivada_a_t(int periodo) {
        if (periodo == 1) return calcular_a_t(1);
        return calcular_a_t(periodo) - calcular_a_t(periodo - 1);
    }

    @Override
    public double derivada_A_t(int periodo) {
        if (periodo == 1) return calcular_A_t(1);
        return calcular_A_t(periodo) - calcular_A_t(periodo - 1);
    }

    @Override
    public double derivada_I_t(int periodo) {
        if (periodo == 1) return calcular_I_t(1);
        return calcular_I_t(periodo) - calcular_I_t(periodo - 1);
    }

    @Override
    public double derivada_S_t(int periodo) {
        if (periodo == 1) return calcular_S_t(1);
        return calcular_S_t(periodo) - calcular_S_t(periodo - 1);
    }



    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        double delta = 1e-4;
        double orig = capital;

        capital = orig + delta;
        double plus = calcularSaldo(periodo);

        capital = orig - delta;
        double minus = calcularSaldo(periodo);

        capital = orig;
        return (plus - minus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        double delta = 1e-4;
        double orig = tasaInteres;

        tasaInteres = orig + delta;
        double plus = calcularSaldo(periodo);

        tasaInteres = orig - delta;
        double minus = calcularSaldo(periodo);

        tasaInteres = orig;
        return (plus - minus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        if (periodo == 1) return calcularSaldo(1);
        return calcularSaldo(periodo) - calcularSaldo(periodo - 1);
    }
}
