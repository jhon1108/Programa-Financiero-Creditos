package servicios;

import modelo.*;

import java.util.List;

public class EvaluadorCredito {

    public boolean evaluar(Usuario usuario, Credito credito, List<ResultadoPeriodico> resultados) {
        if (usuario == null || credito == null || resultados == null || resultados.isEmpty()) {
            return false;
        }

        // Evaluación financiera usando ViabilidadCredito
        ViabilidadCredito viabilidad = new ViabilidadCredito(usuario, credito);
        boolean esFinancieramenteViable = viabilidad.esViable();

        // Evaluación técnica del crédito
        boolean tieneAmortizacion = resultados.stream().anyMatch(r -> r.getAmortizacion() > 0);
        boolean saldoDisminuye = resultados.get(0).getSaldo() > resultados.get(resultados.size() - 1).getSaldo();

        return esFinancieramenteViable && tieneAmortizacion && saldoDisminuye;
    }
}
