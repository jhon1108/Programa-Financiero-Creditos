package modelo;

public abstract class MetodoAmortizacion {
    protected double capital;
    protected double tasaInteres;
    protected int numeroCuotas;

    public MetodoAmortizacion(double capital, double tasaInteres, int numeroCuotas) {
        if (capital <= 0 || tasaInteres < 0 || numeroCuotas <= 0) {
            throw new IllegalArgumentException("Capital, tasa de interés y número de cuotas deben ser válidos.");
        }
        this.capital = capital;
        this.tasaInteres = tasaInteres;
        this.numeroCuotas = numeroCuotas;
    }

    public double getCapital() { return capital; }
    public double getTasaInteres() { return tasaInteres; }
    public int getNumeroCuotas() { return numeroCuotas; }

    public abstract double calcularCuota(int periodo);
    public abstract double calcularInteres(int periodo, double saldoAnterior);
    public abstract double calcularAmortizacion(int periodo, double cuota, double interes);
    public abstract double calcularSaldo(int periodo);
    public abstract double derivadaSaldoRespectoTasa(int periodo);
    public abstract double derivadaSaldoRespectoCapital(int periodo);
    public abstract double derivadaSaldoRespectoTiempo(int periodo);
}

