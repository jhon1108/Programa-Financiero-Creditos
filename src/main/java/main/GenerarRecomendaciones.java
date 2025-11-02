package main;

import java.util.ArrayList;
import java.util.List;

public class GenerarRecomendaciones {

    private Credito credito;
    private List<ResultadoPeriodico> resultados;
    private Usuario usuario;
    private ViabilidadCredito viabilidad;

    public GenerarRecomendaciones(Credito credito, List<ResultadoPeriodico> resultados, Usuario usuario, ViabilidadCredito viabilidad) {
        this.credito = credito;
        this.resultados = resultados;
        this.usuario = usuario;
        this.viabilidad = viabilidad;
    }

    // Recomendación basada en viabilidad del usuario
    public String evaluarViabilidadUsuario() {
        if (!viabilidad.esViable()) {
            return "⚠️ El crédito no es viable para el usuario " + usuario.getNombre() +
                    ". La cuota promedio excede su capacidad mensual. Considera reducir el capital, aumentar las cuotas o elegir otro método.";
        }
        return "✅ El crédito es viable para el usuario según su perfil financiero.";
    }

    // Recomendación basada en el método actual
    public String recomendarMetodoAlternativo() {
        String metodoActual = credito.getMetodoAmortizacion().getClass().getSimpleName();
        double saldoFinal = resultados.get(resultados.size() - 1).getSaldo();

        if (saldoFinal < 0) {
            return "El saldo final es negativo. Considera un método con amortización creciente como el Alemán.";
        }

        if (metodoActual.equals("MetodoAmericano")) {
            return "El método Americano puede generar una gran cuota final. Considera el método Francés si prefieres cuotas constantes.";
        }

        return "El método seleccionado parece adecuado según los resultados.";
    }

    // Evaluación de sensibilidad a la tasa
    public String evaluarSensibilidad() {
        double maxSensibilidadTasa = resultados.stream()
                .mapToDouble(ResultadoPeriodico::getDerivadaTasa)
                .max()
                .orElse(0);

        if (maxSensibilidadTasa > 1.0) {
            return "Alta sensibilidad a la tasa de interés. Pequeños cambios pueden afectar significativamente el saldo.";
        }

        return "La sensibilidad a la tasa es moderada.";
    }

    // Sugerencias de ajustes técnicos
    public String sugerirAjustes() {
        double saldoFinal = resultados.get(resultados.size() - 1).getSaldo();

        if (saldoFinal < 0) {
            return "Considera aumentar el número de cuotas o reducir la tasa de interés.";
        }

        return "No se requieren ajustes importantes.";
    }

    // Genera el resumen completo de recomendaciones
    public List<String> generarResumenRecomendaciones() {
        List<String> recomendaciones = new ArrayList<>();
        recomendaciones.add(evaluarViabilidadUsuario());
        recomendaciones.add(recomendarMetodoAlternativo());
        recomendaciones.add(evaluarSensibilidad());
        recomendaciones.add(sugerirAjustes());
        return recomendaciones;
    }
}