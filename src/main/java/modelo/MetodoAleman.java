package modelo;

public class MetodoAleman extends MetodoAmortizacion {

    public MetodoAleman(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        double amortizacion = capital / numeroCuotas;
        double saldo = capital - amortizacion * periodo;
        double interes = tasaInteres * saldo;
        return amortizacion + interes;
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
        double amortizacion = capital / numeroCuotas;
        return capital - amortizacion * periodo;
    }

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        return 1 - ((double) periodo / numeroCuotas);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        return 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        return -capital / numeroCuotas;
    }
}
