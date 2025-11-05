package modelo;

import java.util.ArrayList;
import java.util.List;

public class GenerarRecomendaciones {

    private Credito credito;
    private List<ResultadoPeriodico> resultados;
    private Usuario usuario;
    private ViabilidadCredito viabilidad;

    public GenerarRecomendaciones(Credito credito, List<ResultadoPeriodico> resultados, Usuario usuario, ViabilidadCredito viabilidad) {
        if (credito == null || resultados == null || resultados.isEmpty() || usuario == null || viabilidad == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos ni vacíos.");
        }
        this.credito = credito;
        this.resultados = resultados;
        this.usuario = usuario;
        this.viabilidad = viabilidad;
    }

    // Recomendación basada en viabilidad del usuario
    public String evaluarViabilidadUsuario() {
        if (!viabilidad.esViable()) {
            return "El crédito no es viable para el usuario " + usuario.getNombre() +
                    ". La cuota promedio excede su capacidad mensual. Se recomienda ajustar el capital, el número de cuotas o el método de amortización.";
        }
        return "El crédito es viable para el usuario según su perfil financiero.";
    }

    // Recomendación basada en el método actual
    public String recomendarMetodoAlternativo() {
        String metodoActual = credito.getMetodoAmortizacion().getClass().getSimpleName();
        double saldoFinal = resultados.get(resultados.size() - 1).getSaldo();

        if (saldoFinal < 0) {
            return "El saldo final es negativo. Se recomienda considerar un método con amortización progresiva.";
        }

        if (metodoActual.equals("MetodoAmericano")) {
            return "El método Americano puede generar una cuota final elevada. Se recomienda considerar un método con cuotas más equilibradas.";
        }

        return "El método seleccionado es adecuado según los resultados obtenidos.";
    }

    // Evaluación de sensibilidad a la tasa
    public String evaluarSensibilidad() {
        double maxSensibilidadTasa = resultados.stream()
                .mapToDouble(ResultadoPeriodico::getDerivadaTasa)
                .max()
                .orElse(0);

        if (maxSensibilidadTasa > 1.0) {
            return "La sensibilidad a la tasa de interés es alta. Cambios pequeños pueden afectar el saldo de forma significativa.";
        }

        return "La sensibilidad a la tasa de interés es aceptable.";
    }

    // Sugerencias de ajustes técnicos
    public String sugerirAjustes() {
        double saldoFinal = resultados.get(resultados.size() - 1).getSaldo();

        if (saldoFinal < 0) {
            return "Se recomienda aumentar el número de cuotas o reducir la tasa de interés para evitar saldo negativo.";
        }

        return "No se requieren ajustes técnicos importantes.";
    }

    // Evaluación adicional basada en indicadores financieros
    public String evaluarIndicadoresFinancieros() {
        double cmp = (usuario.getIngresos() - usuario.getGastos()) * 0.35;
        double ce = (usuario.getGastos() + usuario.getDeudas()) / usuario.getIngresos();

        if (cmp < credito.obtenerCuota(1)) {
            return "La capacidad máxima de pago es insuficiente para cubrir la primera cuota. Se recomienda revisar el monto solicitado.";
        }

        if (ce > 0.4) {
            return "La carga financiera del usuario es elevada. Se recomienda revisar sus gastos y deudas antes de solicitar el crédito.";
        }

        return "Los indicadores financieros del usuario son adecuados para el crédito solicitado.";
    }

    // Genera el resumen completo de recomendaciones
    public List<String> generarResumenRecomendaciones() {
        List<String> recomendaciones = new ArrayList<>();
        recomendaciones.add(evaluarViabilidadUsuario());
        recomendaciones.add(recomendarMetodoAlternativo());
        recomendaciones.add(evaluarSensibilidad());
        recomendaciones.add(sugerirAjustes());
        recomendaciones.add(evaluarIndicadoresFinancieros());
        return recomendaciones;
    }
}