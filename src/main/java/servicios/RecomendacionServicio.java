package servicios;

import modelo.*;

import java.util.List;

public class RecomendacionServicio {
    public List<String> generarRecomendaciones(Credito credito, List<ResultadoPeriodico> resultados, Usuario usuario) {
        ViabilidadCredito viabilidad = new ViabilidadCredito(usuario, credito);
        GenerarRecomendaciones generador = new GenerarRecomendaciones(credito, resultados, usuario, viabilidad);
        return generador.generarResumenRecomendaciones();
    }
}