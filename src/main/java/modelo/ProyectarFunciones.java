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

        MetodoAmortizacion metodo = credito.getMetodoAmortizacion();

        double saldoAnterior = metodo.getCapital();
        int n = metodo.getNumeroCuotas();

        for (int periodo = 1; periodo <= n; periodo++) {

            double cuota = metodo.calcularCuota(periodo);
            double interes = metodo.calcularInteres(periodo, saldoAnterior);
            double amortizacion = metodo.calcularAmortizacion(periodo, cuota, interes);
            double saldo = metodo.calcularSaldo(periodo);

            double dTasa = metodo.derivadaSaldoRespectoTasa(periodo);
            double dCapital = metodo.derivadaSaldoRespectoCapital(periodo);
            double dTiempo = metodo.derivadaSaldoRespectoTiempo(periodo);

            PeriodoCredito p = new PeriodoCredito(
                    periodo,
                    cuota,
                    interes,
                    amortizacion,
                    saldo,
                    dTasa,
                    dCapital,
                    dTiempo
            );

            lista.add(p);
            saldoAnterior = saldo;
        }

        return lista;
    }
    //En que periodo decrece mas rapidamente mi deuda

    public int periodoMayorReduccionDeuda() {

        if (tabla.isEmpty()) {
            return -1;
        }

        double maxReduccion = 0;
        int periodoClave = tabla.get(0).getPeriodo();

        for (int i = 1; i < tabla.size(); i++) {

            double saldoAnterior = tabla.get(i - 1).getSaldo();
            double saldoActual = tabla.get(i).getSaldo();
            double reduccion = saldoAnterior - saldoActual;

            if (reduccion > maxReduccion) {
                maxReduccion = reduccion;
                periodoClave = tabla.get(i).getPeriodo();
            }
        }

        return periodoClave;
    }
}
