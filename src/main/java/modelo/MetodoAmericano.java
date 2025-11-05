package modelo;

public class MetodoAmericano extends MetodoAmortizacion {

    public MetodoAmericano(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        validarPeriodo(periodo);
        return (periodo < numeroCuotas) ? capital * tasaInteres : (capital * tasaInteres) + capital;
    }

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        validarPeriodo(periodo);
        if (saldoAnterior < 0) throw new IllegalArgumentException("El saldo anterior no puede ser negativo.");
        return (periodo <= numeroCuotas) ? capital * tasaInteres : 0;
    }

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        validarPeriodo(periodo);
        if (cuota < 0 || interes < 0) throw new IllegalArgumentException("Cuota e interés deben ser positivos.");
        return (periodo < numeroCuotas) ? 0 : capital;
    }

    @Override
    public double calcularSaldo(int periodo) {
        validarPeriodo(periodo);
        return (periodo < numeroCuotas) ? capital : 0;
    }

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        validarPeriodo(periodo);
        return (periodo < numeroCuotas) ? 1.0 : 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        validarPeriodo(periodo);
        return 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        validarPeriodo(periodo);
        return 0.0;
    }

    private void validarPeriodo(int periodo) {
        if (periodo < 1 || periodo > numeroCuotas) {
            throw new IllegalArgumentException("El periodo debe estar entre 1 y " + numeroCuotas);
        }
    }
}