package main;


public class MetodoGeometrico extends MetodoAmortizacion {

    private double cuotaInicial;
    private double factorCrecimiento;

    public MetodoGeometrico(double capital, double tasaInteres, int numeroCuotas,
                            double cuotaInicial, double factorCrecimiento) {
        super(capital, tasaInteres, numeroCuotas);

        if (cuotaInicial <= 0 || factorCrecimiento <= 0) {
            throw new IllegalArgumentException("La cuota inicial y el factor de crecimiento deben ser mayores que cero.");
        }

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

    //Calcula el saldo restante después de cierto periodo
    @Override
    public double calcularSaldo(int periodo) {
        double saldo = capital;
        for (int t = 1; t <= periodo; t++) {
            double cuota = calcularCuota(t);
            double interes = calcularInteres(t, saldo);
            double amortizacion = calcularAmortizacion(t, cuota, interes);
            saldo -= amortizacion;
        }
        return saldo;
    }

    //Derivada numérica del saldo respecto al capital inicial

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

    //Derivada numérica del saldo respecto a la tasa de interés

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

    //Derivada del saldo respecto al tiempo (diferencia entre periodos)

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        if (periodo <= 1) return 0.0;
        double saldoActual = calcularSaldo(periodo);
        double saldoAnterior = calcularSaldo(periodo - 1);
        return saldoActual - saldoAnterior;
    }

    //Calcula todas las cuotas en un arreglo

    public double[] calcularTodasLasCuotas() {
        double[] cuotas = new double[numeroCuotas];
        for (int i = 0; i < numeroCuotas; i++) {
            cuotas[i] = calcularCuota(i + 1);
        }
        return cuotas;
    }

    //Calcula la cuota promedio del crédito.

    public double calcularCuotaPromedio() {
        double suma = 0;
        for (int i = 1; i <= numeroCuotas; i++) {
            suma += calcularCuota(i);
        }
        return suma / numeroCuotas;
    }
}