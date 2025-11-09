package modelo;

public class MetodoAmericano extends MetodoAmortizacion {

    public MetodoAmericano(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        return (periodo < numeroCuotas)
                ? capital * tasaInteres
                : capital * tasaInteres + capital;
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
    public double calcular_a_t(int periodo) {
        return calcularAmortizacion(periodo, calcularCuota(periodo), calcular_I_t(periodo));
    }

    @Override
    public double calcular_A_t(int periodo) {
        return (periodo < numeroCuotas) ? 0 : capital;
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
        if (periodo == 1) return calcular_a_t(1);
        return calcular_a_t(periodo) - calcular_a_t(periodo - 1);
    }

    @Override
    public double derivada_A_t(int periodo) {
        if (periodo == 1) return calcular_A_t(1);
        return calcular_A_t(periodo) - calcular_A_t(periodo - 1);
    }

    @Override
    public double derivada_I_t(int periodo) {
        if (periodo == 1) return calcular_I_t(1);
        return calcular_I_t(periodo) - calcular_I_t(periodo - 1);
    }

    @Override
    public double derivada_S_t(int periodo) {
        if (periodo == 1) return calcular_S_t(1);
        return calcular_S_t(periodo) - calcular_S_t(periodo - 1);
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
