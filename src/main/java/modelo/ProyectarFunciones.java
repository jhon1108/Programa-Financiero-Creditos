package modelo;

import java.util.ArrayList;
import java.util.List;

public class ProyectarFunciones {

    private final Credito credito;
    private final List<PeriodoCredito> tabla;

    public ProyectarFunciones(Credito credito) {
        this.credito = credito;
        this.tabla = generarTabla();
    }

    public List<PeriodoCredito> getTabla() {
        return tabla;
    }

    private List<PeriodoCredito> generarTabla() {
        List<PeriodoCredito> lista = new ArrayList<>();
        int n = credito.getMetodoAmortizacion().getNumeroCuotas();


        double dS_dTasa_T = credito.sensibilidadRespectoTasa(n);
        double dS_dCapital_T = credito.sensibilidadRespectoCapital(n);
        double dS_dTiempo_T = credito.sensibilidadRespectoTiempo(n);

        for (int t = 1; t <= n; t++) {


            double a_t = credito.calcularAmortizacionPeriodo(t);
            double A_t = credito.calcularAmortizacionAcumulada(t);
            double I_t = credito.calcularInteresPeriodo(t);
            double S_t = credito.calcularSaldoPeriodo(t);


            double a_t_p = credito.derivadaAmortizacion(t);
            double A_t_p = credito.derivadaAmortizacionAcumulada(t);
            double I_t_p = credito.derivadaInteres(t);
            double S_t_p = credito.derivadaSaldo(t);

            PeriodoCredito p = new PeriodoCredito(t, a_t, A_t, I_t, S_t, a_t_p, A_t_p, I_t_p, S_t_p,
                    dS_dTasa_T, dS_dCapital_T, dS_dTiempo_T
            );

            lista.add(p);
        }

        return lista;
    }
}