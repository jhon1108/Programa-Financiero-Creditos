package modelo;

public class Credito {

    private MetodoAmortizacion metodo;

    public Credito(MetodoAmortizacion metodo) {
        if (metodo == null) {
            throw new IllegalArgumentException("El método de amortización no puede ser nulo.");
        }
        this.metodo = metodo;
    }

    public MetodoAmortizacion getMetodoAmortizacion() {
        return metodo;
    }

    public void setMetodoAmortizacion(MetodoAmortizacion metodo) {
        if (metodo == null) {
            throw new IllegalArgumentException("El método de amortización no puede ser nulo.");
        }
        this.metodo = metodo;
    }

    public double obtenerCuota(int periodo) {
        validarPeriodo(periodo);
        return metodo.calcularCuota(periodo);
    }

    public double obtenerSaldo(int periodo) {
        validarPeriodo(periodo);
        return metodo.calcularSaldo(periodo);
    }

    public double sensibilidadRespectoCapital(int periodo) {
        validarPeriodo(periodo);
        return metodo.derivadaSaldoRespectoCapital(periodo);
    }

    public double sensibilidadRespectoTasa(int periodo) {
        validarPeriodo(periodo);
        return metodo.derivadaSaldoRespectoTasa(periodo);
    }

    public double sensibilidadRespectoTiempo(int periodo) {
        validarPeriodo(periodo);
        return metodo.derivadaSaldoRespectoTiempo(periodo);
    }

    public double obtenerInteres(int periodo, double saldoAnterior) {
        validarPeriodo(periodo);
        if (saldoAnterior < 0) {
            throw new IllegalArgumentException("El saldo anterior no puede ser negativo.");
        }
        return metodo.calcularInteres(periodo, saldoAnterior);
    }

    public double obtenerAmortizacion(int periodo, double cuota, double interes) {
        validarPeriodo(periodo);
        if (cuota < 0 || interes < 0) {
            throw new IllegalArgumentException("La cuota y el interés no pueden ser negativos.");
        }
        return metodo.calcularAmortizacion(periodo, cuota, interes);
    }

    private void validarPeriodo(int periodo) {
        if (periodo < 1) {
            throw new IllegalArgumentException("El periodo debe ser mayor o igual a 1.");
        }
    }
}

