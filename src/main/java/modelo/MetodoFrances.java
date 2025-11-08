package modelo;

public class MetodoFrances extends MetodoAmortizacion {

    public MetodoFrances(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        double r = tasaInteres;
        int n = numeroCuotas;
        if (r == 0) return capital / n;
        return capital * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        return saldoAnterior * tasaInteres;
    }

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        return cuota - interes;
    }

    @Override
    public double calcularSaldo(int periodo) {
        double cuota = calcularCuota(periodo);
        double saldo = capital;
        for (int t = 1; t <= periodo; t++) {
            double interes = saldo * tasaInteres;
            double amortizacion = cuota - interes;
            saldo -= amortizacion;
        }
        return saldo;
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        double cuota = calcularCuota(periodo);
        double saldo = capital;
        double derivada = 0;
        for (int t = 1; t <= periodo; t++) {
            double interes = saldo * tasaInteres;
            double amortizacion = cuota - interes;
            saldo -= amortizacion;
            derivada += -saldo * t;
        }
        return derivada;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        double cuota = calcularCuota(periodo);
        double saldo = capital;
        double derivada = 0;
        for (int t = 1; t <= periodo; t++) {
            double interes = saldo * tasaInteres;
            double amortizacion = cuota - interes;
            saldo -= amortizacion;
            derivada += -tasaInteres * saldo;
        }
        return derivada;
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
}
