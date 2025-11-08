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


    public double calcularCuota(int periodo) {
        return metodo.calcularCuota(periodo);
    }

    public double calcularSaldo(int periodo) {
        return metodo.calcularSaldo(periodo);
    }

    public double calcularInteres(int periodo, double saldoAnterior) {
        return metodo.calcularInteres(periodo, saldoAnterior);
    }

    public double calcularAmortizacion(int periodo, double cuota, double interes) {
        return metodo.calcularAmortizacion(periodo, cuota, interes);
    }

    // Sensibilidad
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
