package main;

public class ResultadoPeriodico {
    private int periodo;
    private double cuota;
    private double interes;
    private double amortizacion;
    private double saldo;
    private double derivadaTasa;
    private double derivadaCapital;
    private double derivadaTiempo;

    public ResultadoPeriodico(int periodo, double cuota, double interes, double amortizacion,double saldo, double derivadaTasa, double derivadaCapital, double derivadaTiempo) {
        this.periodo = periodo;
        this.cuota = cuota;
        this.interes = interes;
        this.amortizacion = amortizacion;
        this.saldo = saldo;
        this.derivadaTasa = derivadaTasa;
        this.derivadaCapital = derivadaCapital;
        this.derivadaTiempo = derivadaTiempo;
    }

    public int getPeriodo() { return periodo; }
    public double getCuota() { return cuota; }
    public double getInteres() { return interes; }
    public double getAmortizacion() { return amortizacion; }
    public double getSaldo() { return saldo; }
    public double getDerivadaTasa() { return derivadaTasa; }
    public double getDerivadaCapital() { return derivadaCapital; }
    public double getDerivadaTiempo() { return derivadaTiempo; }
}