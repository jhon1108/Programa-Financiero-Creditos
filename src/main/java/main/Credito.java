package main;

public class Credito {

    private MetodoAmortizacion metodo;

    public Credito(MetodoAmortizacion metodo) {
        this.metodo = metodo;
    }

    public MetodoAmortizacion getMetodoAmortizacion() {
        return metodo;
    }

    public void setMetodoAmortizacion(MetodoAmortizacion metodo) {
        this.metodo = metodo;
    }


    public double obtenerCuota(int periodo) {
        return metodo.calcularCuota(periodo);
    }

    public double obtenerSaldo(int periodo) {
        return metodo.calcularSaldo(periodo);
    }

    public double sensibilidadRespectoCapital(int periodo) {
        return metodo.derivadaSaldoRespectoCapital(periodo);
    }

    public double sensibilidadRespectoTasa(int periodo) {
        return metodo.derivadaSaldoRespectoTasa(periodo);
    }

    public double sensibilidadRespectoTiempo(int periodo) {
        return metodo.derivadaSaldoRespectoTiempo(periodo);
    }
    public double obtenerInteres(int periodo, double saldoAnterior) {
        return metodo.calcularInteres(periodo, saldoAnterior);
    }

    public double obtenerAmortizacion(int periodo, double cuota, double interes) {
        return metodo.calcularAmortizacion(periodo, cuota, interes);
    }
}

