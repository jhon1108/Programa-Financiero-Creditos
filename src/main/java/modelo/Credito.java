package modelo;

public class Credito {

    private Usuario usuario;
    private MetodoAmortizacion metodo;

    public Credito(Usuario usuario, MetodoAmortizacion metodo) {
        this.usuario = usuario;
        this.metodo = metodo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public MetodoAmortizacion getMetodoAmortizacion() {
        return metodo;
    }


    public double calcularAmortizacionPeriodo(int periodo) {
        return metodo.calcular_a_t(periodo);
    }

    public double calcularAmortizacionAcumulada(int periodo) {
        return metodo.calcular_A_t(periodo);
    }

    public double calcularInteresPeriodo(int periodo) {
        return metodo.calcular_I_t(periodo);
    }

    public double calcularSaldoPeriodo(int periodo) {
        return metodo.calcular_S_t(periodo);
    }


    public double derivadaAmortizacion(int periodo) {
        return metodo.derivada_a_t(periodo);
    }

    public double derivadaAmortizacionAcumulada(int periodo) {
        return metodo.derivada_A_t(periodo);
    }

    public double derivadaInteres(int periodo) {
        return metodo.derivada_I_t(periodo);
    }

    public double derivadaSaldo(int periodo) {
        return metodo.derivada_S_t(periodo);
    }


    public double sensibilidadRespectoTasa(int periodo) {
        return metodo.derivadaSaldoRespectoTasa(periodo);
    }

    public double sensibilidadRespectoCapital(int periodo) {
        return metodo.derivadaSaldoRespectoCapital(periodo);
    }

    public double sensibilidadRespectoTiempo(int periodo) {
        return metodo.derivadaSaldoRespectoTiempo(periodo);
    }
}