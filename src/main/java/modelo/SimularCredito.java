package modelo;

import java.util.ArrayList;
import java.util.List;

public class SimularCredito {

    private final Credito credito;

    public SimularCredito(Credito credito) {
        if (credito == null) {
            throw new IllegalArgumentException("El crédito no puede ser nulo.");
        }
        this.credito = credito;
    }

    public List<ResultadoPeriodico> simular() {
        List<ResultadoPeriodico> resultados = new ArrayList<>();

        // Usamos el capital como saldo inicial
        double saldoAnterior = credito.getMetodoAmortizacion().getCapital();
        int numeroCuotas = credito.getMetodoAmortizacion().getNumeroCuotas();

        for (int t = 1; t <= numeroCuotas; t++) {
            double cuota = credito.obtenerCuota(t);
            double interes = credito.obtenerInteres(t, saldoAnterior);
            double amortizacion = cuota - interes;
            double saldo = credito.obtenerSaldo(t);

            //  Protección contra valores negativos
            if (cuota < 0) cuota = 0;
            if (interes < 0) interes = 0;
            if (amortizacion < 0) amortizacion = 0;
            if (saldo < 0) saldo = 0;

            double dTasa = credito.sensibilidadRespectoTasa(t);
            double dCapital = credito.sensibilidadRespectoCapital(t);
            double dTiempo = credito.sensibilidadRespectoTiempo(t);

            ResultadoPeriodico resultado = new ResultadoPeriodico(t, cuota, interes, amortizacion, saldo, dTasa, dCapital, dTiempo);

            resultados.add(resultado);
            saldoAnterior = saldo;
        }

        return resultados;
    }
}