package modelo;

public class AnalizarSensibilidad {

    private final Credito credito;

    public AnalizarSensibilidad(Credito credito) {
        this.credito = credito;
    }

    public PeriodoCredito obtenerSensibilidadFinal() {

        MetodoAmortizacion m = credito.getMetodoAmortizacion();
        int T = m.getNumeroCuotas();

        double a_t = m.calcular_a_t(T);
        double A_t= m.calcular_A_t(T);
        double I_t = m.calcular_I_t(T);
        double S_t = m.calcular_S_t(T);

        double a_t_p = m.derivada_a_t(T);
        double A_t_p = m.derivada_A_t(T);
        double I_t_p = m.derivada_I_t(T);
        double S_t_p= m.derivada_S_t(T);

        double dTasa     = m.derivadaSaldoRespectoTasa(T);
        double dCapital  = m.derivadaSaldoRespectoCapital(T);
        double dTiempo   = m.derivadaSaldoRespectoTiempo(T);

        return new PeriodoCredito(T, a_t, A_t, I_t, S_t, a_t_p, A_t_p, I_t_p, S_t_p, dTasa, dCapital, dTiempo
        );
    }
}
