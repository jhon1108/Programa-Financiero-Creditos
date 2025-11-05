package modelo;

public class MetodoAritmetico extends MetodoAmortizacion {

    private double cuotaBase;
    private double incremento;

    public MetodoAritmetico(double capital, double tasaInteres, int numeroCuotas, double cuotaBase, double incremento) {
        super(capital, tasaInteres, numeroCuotas);
        if (cuotaBase < 0 || incremento < 0) {
            throw new IllegalArgumentException("La cuota base y el incremento deben ser positivos.");
        }
        this.cuotaBase = cuotaBase;
        this.incremento = incremento;
    }

    @Override
    public double calcularCuota(int periodo) {
        validarPeriodo(periodo);
        return cuotaBase + incremento * periodo;
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
        double saldo = capital;
        for (int t = 1; t <= periodo; t++) {
            double cuota = calcularCuota(t);
            double interes = tasaInteres * saldo;
            double amortizacion = cuota - interes;
            saldo -= amortizacion;
        }
        return saldo;
    }

    @Override
    public double derivadaSaldoRespectoCapital(int periodo) {
        validarPeriodo(periodo);
        double delta = 0.0001;
        double originalCapital = capital;

        capital = originalCapital + delta;
        double saldoPlus = calcularSaldo(periodo);

        capital = originalCapital - delta;
        double saldoMinus = calcularSaldo(periodo);

        capital = originalCapital;
        return (saldoPlus - saldoMinus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTasa(int periodo) {
        validarPeriodo(periodo);
        double delta = 0.0001;
        double originalTasa = tasaInteres;

        tasaInteres = originalTasa + delta;
        double saldoPlus = calcularSaldo(periodo);

        tasaInteres = originalTasa - delta;
        double saldoMinus = calcularSaldo(periodo);

        tasaInteres = originalTasa;
        return (saldoPlus - saldoMinus) / (2 * delta);
    }

    @Override
    public double derivadaSaldoRespectoTiempo(int periodo) {
        validarPeriodo(periodo);
        if (periodo == 1) return calcularSaldo(1); // no hay periodo anterior
        double saldoActual = calcularSaldo(periodo);
        double saldoAnterior = calcularSaldo(periodo - 1);
        return saldoActual - saldoAnterior;
    }

    private void validarPeriodo(int periodo) {
        if (periodo < 1 || periodo > numeroCuotas) {
            throw new IllegalArgumentException("El periodo debe estar entre 1 y " + numeroCuotas);
        }
    }
}