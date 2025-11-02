package main;

public class MetodoAmericano extends MetodoAmortizacion {

    public MetodoAmericano(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        if (periodo < numeroCuotas) {
            return capital * tasaInteres;
        } else {
            return (capital * tasaInteres) + capital;
        }
    }

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        if (periodo <= numeroCuotas) {
            return capital * tasaInteres;
        } else {
            return 0;
        }
    }

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        if (periodo < numeroCuotas) {
            return 0;
        } else {
            return capital;
        }
    }

    @Override
    public double calcularSaldo(int periodo) {
        if (periodo < numeroCuotas) {
            return capital;
        } else {
            return 0;
        }
    }

    //  Derivadas simbólicas (sensibilidad)

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        // El saldo es igual al capital hasta el último periodo
        return (periodo < numeroCuotas) ? 1.0 : 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        // El saldo no depende de la tasa en este método
        return 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        // El saldo no cambia con el tiempo hasta el último periodo
        return 0.0;
    }
}