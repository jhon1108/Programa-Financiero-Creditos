package modelo;

public class MetodoAmericano extends MetodoAmortizacion {

    public MetodoAmericano(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        return (periodo < numeroCuotas) ? capital * tasaInteres : (capital * tasaInteres + capital);
    }

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        return (periodo <= numeroCuotas) ? capital * tasaInteres : 0;
    }

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        return (periodo < numeroCuotas) ? 0 : capital;
    }

    @Override
    public double calcularSaldo(int periodo) {
        return (periodo < numeroCuotas) ? capital : 0;
    }

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        return (periodo < numeroCuotas) ? 1.0 : 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        return 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        return 0.0;
    }
}
