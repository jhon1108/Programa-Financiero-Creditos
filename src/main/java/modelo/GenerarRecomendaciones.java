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
        PeriodoCredito pf = analizador.obtenerSensibilidadFinal();

        double saldoFinal = pf.getS_t();
        double dTasa      = pf.getDsdTasa();
        double dCapital   = pf.getDsdCapital();
        double dTiempo    = pf.getDsdTiempo();

        if (Math.abs(dTasa) > Math.abs(dCapital) && Math.abs(dTasa) > Math.abs(dTiempo)) {
            recomendaciones.add(" El saldo es muy sensible a variaciones en la tasa → considerar refinanciación.");
        }

        if (Math.abs(dCapital) > Math.abs(dTasa) && Math.abs(dCapital) > Math.abs(dTiempo)) {
            recomendaciones.add("Aumentar el capital inicial reduciría significativamente la deuda futura.");
        }

        if (dTiempo < 0) {
            recomendaciones.add(" Abonos adelantados reducen el tiempo total del crédito.");
        }

        if (saldoFinal > credito.getMetodoAmortizacion().getCapital() * 0.3) {
            recomendaciones.add(" El saldo final es alto → buscar mejores condiciones.");
        }

        if (recomendaciones.isEmpty()) {
            recomendaciones.add("Crédito estable. No se recomiendan cambios.");
        }

        return recomendaciones;
    }
}
