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
            double amortizacion = calcularAmortizacion(t, cuota, interes);

            if (amortizacion < 0) amortizacion = 0;
            saldo -= amortizacion;

            if (saldo < 0) saldo = 0;
        }

        return saldo;
    }

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        double delta = 0.0001;
        double originalCapital = capital;

        capital = originalCapital + delta;
        double saldoPlus = calcularSaldo(periodo);

        capital = originalCapital - delta;
        double saldoMinus = calcularSaldo(periodo);

        capital = originalCapital;
        return (saldoPlus - saldoMinus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        double delta = 0.0001;
        double originalTasa = tasaInteres;

        tasaInteres = originalTasa + delta;
        double saldoPlus = calcularSaldo(periodo);

        tasaInteres = originalTasa - delta;
        double saldoMinus = calcularSaldo(periodo);

        tasaInteres = originalTasa;
        return (saldoPlus - saldoMinus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        if (periodo == 1) return calcularSaldo(1);
        double saldoActual = calcularSaldo(periodo);
        double saldoAnterior = calcularSaldo(periodo - 1);
        return saldoActual - saldoAnterior;
    }
}
