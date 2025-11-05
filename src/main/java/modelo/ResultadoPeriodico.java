package modelo;

public class ResultadoPeriodico {

    private final int periodo;
    private final double cuota;
    private final double interes;
    private final double amortizacion;
    private final double saldo;
    private final double derivadaTasa;
    private final double derivadaCapital;
    private final double derivadaTiempo;

    public ResultadoPeriodico(int periodo, double cuota, double interes, double amortizacion,
                              double saldo, double derivadaTasa, double derivadaCapital, double derivadaTiempo) {
        if (periodo < 1) {
            throw new IllegalArgumentException("El periodo debe ser mayor o igual a 1.");
        }
        if (cuota < 0 || interes < 0 || amortizacion < 0 || saldo < 0) {
            throw new IllegalArgumentException("Los valores financieros no pueden ser negativos.");
        }
        this.periodo = periodo;
        this.cuota = cuota;
        this.interes = interes;
        this.amortizacion = amortizacion;
        this.saldo = saldo;
        this.derivadaTasa = derivadaTasa;
        this.derivadaCapital = derivadaCapital;
        this.derivadaTiempo = derivadaTiempo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public double getCuota() {
        return cuota;
    }

    public double getInteres() {
        return interes;
    }

    public double getAmortizacion() {
        return amortizacion;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getDerivadaTasa() {
        return derivadaTasa;
    }

    public double getDerivadaCapital() {
        return derivadaCapital;
    }

    public double getDerivadaTiempo() {
        return derivadaTiempo;
    }
}