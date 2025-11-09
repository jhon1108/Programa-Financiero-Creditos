package modelo;

public class MetodoFrances extends MetodoAmortizacion {

    public MetodoFrances(double capital, double tasaInteres, int numeroCuotas) {
        super(capital, tasaInteres, numeroCuotas);
    }

    @Override
    public double calcularCuota(int periodo) {
        double r = tasaInteres;
        int n = numeroCuotas;
        if (r == 0) return capital / n;

        return capital * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    @Override
    public double calcularInteres(int periodo, double saldoAnterior) {
        return saldoAnterior * tasaInteres;
    }

    @Override
    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        return cuota - interes;
    }

    @Override
    public double calcularSaldo(int periodo) {
        double cuota = calcularCuota(periodo);
        double saldo = capital;

        for (int t = 1; t <= periodo; t++) {
            double interes = saldo * tasaInteres;
            saldo -= (cuota - interes);
        }

        return Math.max(saldo, 0);
    }




    @Override
    public double calcular_a_t(int periodo) {
        double interes = calcular_I_t(periodo);
        double cuota = calcularCuota(periodo);
        return cuota - interes;
    }

    @Override
    public double calcular_A_t(int periodo) {
        double suma = 0;
        for (int t = 1; t <= periodo; t++)
            suma += calcular_a_t(t);
        return suma;
    }

    @Override
    public double calcular_I_t(int periodo) {
        return calcularInteres(periodo, calcularSaldo(periodo - 1));
    }

    @Override
    public double calcular_S_t(int periodo) {
        return calcularSaldo(periodo);
    }


    // =========================================================
    // derivadas a(t), A(t), I(t), S(t)
    // =========================================================

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


    // =========================================================
    // Sensibilidad saldo
    // =========================================================

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        // Numérica
        double delta = 1e-4;
        double orig = tasaInteres;

        tasaInteres = orig + delta;
        double plus = calcularSaldo(periodo);

        tasaInteres = orig - delta;
        double minus = calcularSaldo(periodo);

        tasaInteres = orig;
        return (plus - minus) / (2 * delta);
    }


    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        if (periodo == 1) return calcularSaldo(1);
        return calcularSaldo(periodo) - calcularSaldo(periodo - 1);
    }


    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        double delta = 1e-4;
        double orig = capital;

        capital = orig + delta;
        double plus = calcularSaldo(periodo);

        capital = orig - delta;
        double minus = calcularSaldo(periodo);

        capital = orig;
        return (plus - minus) / (2 * delta);
    }
}
