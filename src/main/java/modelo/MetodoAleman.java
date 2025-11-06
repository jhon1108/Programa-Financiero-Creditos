package modelo;

public class MetodoAleman extends MetodoAmortizacion {

    public MetodoAleman(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        validarPeriodo(periodo);
        double amortizacion = capital / numeroCuotas;
        double saldo = capital - amortizacion * periodo;
        double interes = tasaInteres * saldo;
        return amortizacion + interes;
    }

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        validarPeriodo(periodo);
        if (saldoAnterior < 0) throw new IllegalArgumentException("El saldo anterior no puede ser negativo.");
        return tasaInteres * saldoAnterior;
    }

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        validarPeriodo(periodo);
        if (cuota < 0 || interes < 0) throw new IllegalArgumentException("Cuota e interés deben ser positivos.");
        return cuota - interes;
    }

    @Override
    public double calcularSaldo(int periodo) {
        validarPeriodo(periodo);
        double amortizacion = capital / numeroCuotas;
        return capital - amortizacion * periodo;
    }

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        validarPeriodo(periodo);
        return 1 - ((double) periodo / numeroCuotas);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        validarPeriodo(periodo);
        return 0.0;
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        validarPeriodo(periodo);
        return -capital / numeroCuotas;
    }

    private void validarPeriodo(int periodo) {
        if (periodo < 1 || periodo > numeroCuotas) {
            throw new IllegalArgumentException("El periodo debe estar entre 1 y " + numeroCuotas);
        }
    }
}