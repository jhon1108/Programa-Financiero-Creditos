package modelo;

import java.util.List;

public class ResultadoCredito {

    private final List<ResultadoPeriodico> resultados;
    private final boolean esValido;
    private final List<String> recomendaciones;

    public ResultadoCredito(List<ResultadoPeriodico> resultados, boolean esValido, List<String> recomendaciones) {
        this.resultados = resultados;
        this.esValido = esValido;
        this.recomendaciones = recomendaciones;
    }

    public List<ResultadoPeriodico> getResultados() {
        return resultados;
    }

    public boolean isEsValido() {
        return esValido;
    }

    public List<String> getRecomendaciones() {
        return recomendaciones;
    }
}