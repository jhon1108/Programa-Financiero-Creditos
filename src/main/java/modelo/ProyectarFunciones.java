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
        MetodoAmortizacion m = credito.getMetodoAmortizacion();
        int n = m.getNumeroCuotas();

        double dS_dTasa_T = m.derivadaSaldoRespectoTasa(n);
        double dS_dCapital_T = m.derivadaSaldoRespectoCapital(n);
        double dS_dTiempo_T = m.derivadaSaldoRespectoTiempo(n);

        for (int t = 1; t <= n; t++) {

            double a_t = m.calcular_a_t(t);
            double A_t = m.calcular_A_t(t);
            double I_t = m.calcular_I_t(t);
            double S_t = m.calcular_S_t(t);


            double a_t_p = m.derivada_a_t(t);
            double A_t_p = m.derivada_A_t(t);
            double I_t_p = m.derivada_I_t(t);
            double S_t_p = m.derivada_S_t(t);


            PeriodoCredito p = new PeriodoCredito(t, a_t, A_t, I_t, S_t, a_t_p, A_t_p, I_t_p, S_t_p, dS_dTasa_T, dS_dCapital_T, dS_dTiempo_T
            );

            lista.add(p);
        }
        return lista;
    }
}
