package servicios;

import modelo.*;

import java.util.List;

public class CreditoServicio {

    private final SimulacionServicio simulacionServicio = new SimulacionServicio();
    private final EvaluadorCredito evaluador = new EvaluadorCredito();
    private final RecomendacionServicio recomendacionServicio = new RecomendacionServicio();

    public ResultadoCredito realizarSimulacionGenerarRecomendaciones(Usuario usuario, Credito credito) {
        // 1. Simular el crédito
        List<ResultadoPeriodico> resultados = simulacionServicio.simularCredito(credito);

        // 2. Evaluar viabilidad técnica + financiera
        boolean esValido = evaluador.evaluar(usuario, credito, resultados);

        // 3. Generar recomendaciones
        List<String> recomendaciones = recomendacionServicio.generarRecomendaciones(credito, resultados, usuario);

        // 4. Devolver resultado completo
        return new ResultadoCredito(resultados, esValido, recomendaciones);
    }
}