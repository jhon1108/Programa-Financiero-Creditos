package modelo;

public abstract class MetodoAmortizacion {

    protected double capital;
    protected double tasaInteres;
    protected int numeroCuotas;

    public MetodoAmortizacion(double capital, double tasaInteres, int numeroCuotas) {
        this.capital = capital;
        this.tasaInteres = tasaInteres;
        this.numeroCuotas = numeroCuotas;
    }

    public double getCapital() {
        return capital;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public abstract double calcularCuota(int periodo);
    public abstract double calcularInteres(int periodo, double saldoAnterior);
    public abstract double calcularAmortizacion(int periodo, double cuota, double interes);
    public abstract double calcularSaldo(int periodo);


    public abstract double calcular_a_t(int periodo);
    public abstract double calcular_A_t(int periodo);
    public abstract double calcular_I_t(int periodo);
    public abstract double calcular_S_t(int periodo);

    public abstract double derivada_a_t(int periodo);
    public abstract double derivada_A_t(int periodo);
    public abstract double derivada_I_t(int periodo);
    public abstract double derivada_S_t(int periodo);

    public abstract double derivadaSaldoRespectoTasa(int periodo);
    public abstract double derivadaSaldoRespectoCapital(int periodo);
    public abstract double derivadaSaldoRespectoTiempo(int periodo);
}
