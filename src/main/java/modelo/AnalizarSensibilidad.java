package modelo;

public class AnalizarSensibilidad {

    private final Credito credito;

    public AnalizarSensibilidad(Credito credito) {
        this.credito = credito;
    }

    public PeriodoCredito obtenerSensibilidadFinal() {

        MetodoAmortizacion m = credito.getMetodoAmortizacion();
        int T = m.getNumeroCuotas();

        // Lo necesario para construir PeriodoCredito
        double saldo = m.calcularSaldo(T);
        double cuota = m.calcularCuota(T);
        double interes = m.calcularInteres(T, m.calcularSaldo(T - 1));
        double amortizacion = cuota - interes;

        double dTasa    = m.derivadaSaldoRespectoTasa(T);
        double dCapital = m.derivadaSaldoRespectoCapital(T);
        double dTiempo  = m.derivadaSaldoRespectoTiempo(T);

        return new PeriodoCredito(T,cuota, interes, amortizacion, saldo, dTasa, dCapital, dTiempo
        );
    }
}
