package main;

import java.util.ArrayList;
import java.util.List;

public class SimularCredito {

    private Credito credito;

    public SimularCredito(Credito credito) {
        this.credito = credito;
    }

    public void simular() {
        System.out.printf("%-8s | %-10s | %-10s | %-15s | %-10s | %-10s | %-12s | %-10s\n",
                "Periodo", "Cuota", "Interés", "Amortización", "Saldo", "dS/dTasa", "dS/dCapital", "dS/dTiempo");

        double saldoAnterior = credito.obtenerSaldo(0);
        for (int t = 1; t <= credito.getMetodoAmortizacion().getNumeroCuotas(); t++) {
            double cuota = credito.obtenerCuota(t);
            double interes = credito.obtenerInteres(t, saldoAnterior);
            double amortizacion = credito.obtenerAmortizacion(t, cuota, interes);
            double saldo = credito.obtenerSaldo(t);
            double dTasa = credito.sensibilidadRespectoTasa(t);
            double dCapital = credito.sensibilidadRespectoCapital(t);
            double dTiempo = credito.sensibilidadRespectoTiempo(t);

            System.out.printf("%-8d | %-10.2f | %-10.2f | %-15.2f | %-10.2f | %-10.4f | %-12.4f | %-10.4f\n",
                    t, cuota, interes, amortizacion, saldo, dTasa, dCapital, dTiempo);

            saldoAnterior = saldo;
        }
    }

    public List<ResultadoPeriodico> simularYRetornar() {
        List<ResultadoPeriodico> resultados = new ArrayList<>();
        double saldoAnterior = credito.obtenerSaldo(0);

        for (int t = 1; t <= credito.getMetodoAmortizacion().getNumeroCuotas(); t++) {
            double cuota = credito.obtenerCuota(t);
            double interes = credito.obtenerInteres(t, saldoAnterior);
            double amortizacion = credito.obtenerAmortizacion(t, cuota, interes);
            double saldo = credito.obtenerSaldo(t);
            double dTasa = credito.sensibilidadRespectoTasa(t);
            double dCapital = credito.sensibilidadRespectoCapital(t);
            double dTiempo = credito.sensibilidadRespectoTiempo(t);

            ResultadoPeriodico resultado = new ResultadoPeriodico(
                    t, cuota, interes, amortizacion, saldo, dTasa, dCapital, dTiempo
            );

            resultados.add(resultado);
            saldoAnterior = saldo;
        }

        return resultados;
    }
}