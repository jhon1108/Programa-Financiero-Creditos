package modelo;

public class MetodoAritmetico extends MetodoAmortizacion {

    private double incremento; // d
    private double primeraAmortizacion; // A1

    public MetodoAritmetico(double capital, double tasaInteres, int numeroCuotas, double incremento) {
        super(capital, tasaInteres, numeroCuotas);
        this.incremento = incremento;
        this.primeraAmortizacion = calcularPrimeraAmortizacion(capital, incremento, numeroCuotas);
    }

    private double calcularPrimeraAmortizacion(double C, double d, int n) {
        // A1 = [2C - d * n * (n - 1)] / (2n)
        return (2 * C - d * n * (n - 1)) / (2.0 * n);
    }
    public double calcular_A_t(int periodo) {
        double A_t = primeraAmortizacion + (periodo - 1) * incremento;
        return Math.round(A_t * 100.0) / 100.0;
    }

    // Saldo insoluto al final del periodo t
    @Override
    public double calcular_S_t(int periodo) {
        double S_t = capital - (periodo * (2 * primeraAmortizacion + (periodo - 1) * incremento)) / 2.0;
        if (S_t < 0) S_t = 0;
        return Math.round(S_t * 100.0) / 100.0;
    }

    // Interés del periodo t
    @Override
    public double calcular_I_t(int periodo) {
        double saldoAnterior = (periodo == 1) ? capital : calcular_S_t(periodo - 1);
        double I_t = saldoAnterior * tasaInteres;
        return Math.round(I_t * 100.0) / 100.0;
    }

    // Cuota total del periodo t
    @Override
    public double calcular_a_t(int periodo) {
        double a_t = calcular_A_t(periodo) + calcular_I_t(periodo);
        return Math.round(a_t * 100.0) / 100.0;
    }

    // ===== DERIVADAS (diferencias entre periodos consecutivos) =====

    @Override
    public double derivada_a_t(int periodo) {
        if (periodo == numeroCuotas)
            return calcular_a_t(periodo) - calcular_a_t(periodo - 1);
        return Math.round((calcular_a_t(periodo + 1) - calcular_a_t(periodo)) * 100.0) / 100.0;
    }

    @Override
    public double derivada_A_t(int periodo) {
        if (periodo == numeroCuotas)
            return calcular_A_t(periodo) - calcular_A_t(periodo - 1);
        return Math.round((calcular_A_t(periodo + 1) - calcular_A_t(periodo)) * 100.0) / 100.0;
    }

    @Override
    public double derivada_I_t(int periodo) {
        if (periodo == numeroCuotas)
            return calcular_I_t(periodo) - calcular_I_t(periodo - 1);
        return Math.round((calcular_I_t(periodo + 1) - calcular_I_t(periodo)) * 100.0) / 100.0;
    }

    @Override
    public double derivada_S_t(int periodo) {
        if (periodo == numeroCuotas)
            return calcular_S_t(periodo) - calcular_S_t(periodo - 1);
        return Math.round((calcular_S_t(periodo + 1) - calcular_S_t(periodo)) * 100.0) / 100.0;
    }



    // === DERIVADAS PARCIALES (opcional) ===
    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        double delta = 1e-4;
        double orig = capital;
        capital = orig + delta;
        double plus = calcular_S_t(periodo);
        capital = orig - delta;
        double minus = calcular_S_t(periodo);
        capital = orig;
        return (plus - minus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        double delta = 1e-4;
        double orig = tasaInteres;
        tasaInteres = orig + delta;
        double plus = calcular_S_t(periodo);
        tasaInteres = orig - delta;
        double minus = calcular_S_t(periodo);
        tasaInteres = orig;
        return (plus - minus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        if (periodo < numeroCuotas)
            return calcular_S_t(periodo + 1) - calcular_S_t(periodo);
        else
            return derivadaSaldoRespectoTiempo(periodo - 1);
    }
}
