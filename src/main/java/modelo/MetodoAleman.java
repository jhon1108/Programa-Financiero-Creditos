package modelo;

public class MetodoAleman extends MetodoAmortizacion {

    public MetodoAleman(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        double amort = capital / numeroCuotas;
        double saldo = capital - amort * (periodo - 1);
        double interes = tasaInteres * saldo;
        return amort + interes;
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
        double amort = capital / numeroCuotas;
        return Math.max(capital - amort * periodo, 0);
    }


    @Override
    public double calcular_a_t(int periodo) {
        return capital / numeroCuotas;
    }

    @Override
    public double calcular_A_t(int periodo) {
        return (capital / numeroCuotas) * periodo;
    }

    @Override
    public double calcular_I_t(int periodo) {
        return calcularInteres(periodo, calcularSaldo(periodo - 1));
    }

    @Override
    public double calcular_S_t(int periodo) {
        return calcularSaldo(periodo);
    }



    @Override
    public double derivada_a_t(int periodo) {
        return 0;
    }

    @Override
    public double derivada_A_t(int periodo) {
        return capital / numeroCuotas;
    }

    @Override
    public double derivada_I_t(int periodo) {
        return calcular_I_t(periodo) - calcular_I_t(periodo - 1);
    }

    @Override
    public double derivada_S_t(int periodo) {
        return calcular_S_t(periodo) - calcular_S_t(periodo - 1);
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
