package main;

public class MetodoAritmetico extends MetodoAmortizacion {

    private double cuotaBase;
    private double incremento;

    public MetodoAritmetico(double capital, double tasaInteres, int numeroCuotas, double cuotaBase, double incremento) {
        super(capital, tasaInteres, numeroCuotas);
        this.cuotaBase = cuotaBase;
        this.incremento = incremento;
    }

    @Override
    public double calcularCuota(int periodo) {
        return cuotaBase + incremento * periodo;
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
            double interes = tasaInteres * saldo;
            double amortizacion = cuota - interes;
            saldo -= amortizacion;
        }
        return saldo;
    }

    //  Derivadas simbólicas (sensibilidad)

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        // Aproximación numérica
        double delta = 0.0001;
        double originalCapital = capital;

        capital = originalCapital + delta;
        double saldoPlus = calcularSaldo(periodo);

        capital = originalCapital - delta;
        double saldoMinus = calcularSaldo(periodo);

        capital = originalCapital; // restaurar

        return (saldoPlus - saldoMinus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        // Aproximación numérica
        double delta = 0.0001;
        double originalTasa = tasaInteres;

        tasaInteres = originalTasa + delta;
        double saldoPlus = calcularSaldo(periodo);

        tasaInteres = originalTasa - delta;
        double saldoMinus = calcularSaldo(periodo);

        tasaInteres = originalTasa; // restaurar

        return (saldoPlus - saldoMinus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        // Aproximación numérica
        double saldoActual = calcularSaldo(periodo);
        double saldoAnterior = calcularSaldo(periodo - 1);
        return saldoActual - saldoAnterior;
    }
}