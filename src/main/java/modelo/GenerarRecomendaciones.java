package modelo;

import java.util.ArrayList;
import java.util.List;

public class GenerarRecomendaciones {

    private final Credito credito;
    private final AnalizarSensibilidad analizador;

    public GenerarRecomendaciones(Credito credito) {
        this.credito = credito;
        this.analizador = new AnalizarSensibilidad(credito);
    }

    public List<String> obtenerRecomendaciones() {

        List<String> recomendaciones = new ArrayList<>();
        MetodoAmortizacion m = credito.getMetodoAmortizacion();
        int T = m.getNumeroCuotas();

        PeriodoCredito pFinal = analizador.obtenerSensibilidadFinal();

        double saldoFinal = pFinal.getSaldo();
        double dTasa = pFinal.getDerivadaTasa();
        double dCapital = pFinal.getDerivadaCapital();
        double dTiempo = pFinal.getDerivadaTiempo();

        // 1. Sensibilidad respecto a tasa
        if (Math.abs(dTasa) > Math.abs(dCapital) && Math.abs(dTasa) > Math.abs(dTiempo)) {
            recomendaciones.add("Tu saldo es más sensible a cambios en la tasa de interés. Considera refinanciar si las tasas bajan.");
        }

        // 2. Sensibilidad respecto a capital
        if (Math.abs(dCapital) > Math.abs(dTasa) && Math.abs(dCapital) > Math.abs(dTiempo)) {
            recomendaciones.add("Aumentar el pago inicial reduciría significativamente tu deuda futura.");
        }

        // 3. Abonos adicionales
        if (dTiempo < 0) {
            recomendaciones.add("Realizar pagos adicionales podría reducir el tiempo total del crédito.");
        }

        // 4. Si queda saldo alto al final
        if (saldoFinal > credito.getMetodoAmortizacion().getCapital() * 0.3) {
            recomendaciones.add("El saldo restante es elevado. Se recomienda evaluar mejores condiciones.");
        }

        // Si no se generó ninguna recomendación
        if (recomendaciones.isEmpty()) {
            recomendaciones.add("El crédito se encuentra en condiciones estables. No se recomiendan cambios.");
        }

        return recomendaciones;
    }
}
