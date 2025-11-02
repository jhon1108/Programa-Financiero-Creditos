package main;

public class MetodoAleman extends MetodoAmortizacion {


    public MetodoAleman(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);

        if (capital <= 0 || tasaInteres < 0 || numeroCuotas <= 0) {
            throw new IllegalArgumentException("Capital, tasa de interés y número de cuotas deben ser positivos.");
        }
    }

    //Calcula la cuota del periodo.
    //Cuota = amortización fija + interés sobre saldo restante.

    @Override
    public double calcularCuota(int periodo) {
        double amortizacion = capital / numeroCuotas;
        double saldo = capital - amortizacion * periodo;
        double interes = tasaInteres * saldo;
        return amortizacion + interes;
    }

    //Calcula el interés del periodo según el saldo anterior.

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        return tasaInteres * saldoAnterior;
    }

    //Calcula la amortización del periodo (cuota - interés).

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        return cuota - interes;
    }

    //Calcula el saldo restante después de cierto periodo.

    @Override
    public double calcularSaldo(int periodo) {
        double amortizacion = capital / numeroCuotas;
        return capital - amortizacion * periodo;
    }

    //Derivada del saldo respecto al capital inicial.

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        return 1 - ((double) periodo / numeroCuotas);
    }

    //Derivada del saldo respecto a la tasa de interés.

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        return 0.0;
    }


    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        return -capital / numeroCuotas;
    }


    public double[] calcularTodasLasCuotas() {
        double[] cuotas = new double[numeroCuotas];
        for (int i = 0; i < numeroCuotas; i++) {
            cuotas[i] = calcularCuota(i + 1);
        }
        return cuotas;
    }


    public double calcularCuotaPromedio() {
        double suma = 0;
        for (int i = 1; i <= numeroCuotas; i++) {
            suma += calcularCuota(i);
        }
        return suma / numeroCuotas;
    }
}