package modelo;

import java.util.List;

public class TablaCredito {
    private final List<PeriodoCredito> periodos;

    public TablaCredito(List<PeriodoCredito> periodos) {
        this.periodos = periodos;
    }

    public List<PeriodoCredito> getPeriodos() {
        return periodos;
    }

    public int periodoMayorReduccionDeuda() {
        double maxReduccion = 0;
        int periodoClave = 1;

        for (int i = 1; i < periodos.size(); i++) {
            double saldoAnterior = periodos.get(i - 1).getSaldo();
            double saldoActual = periodos.get(i).getSaldo();
            double reduccion = saldoAnterior - saldoActual;

            if (reduccion > maxReduccion) {
                maxReduccion = reduccion;
                periodoClave = periodos.get(i).getPeriodo();
            }
        }

        return periodoClave;
    }

    public int periodoMayorInteres() {
        double maxInteres = 0;
        int periodoClave = 1;

        for (PeriodoCredito p : periodos) {
            if (p.getInteres() > maxInteres) {
                maxInteres = p.getInteres();
                periodoClave = p.getPeriodo();
            }
        }

        return periodoClave;
    }

    public double totalPagado() {
        return periodos.stream().mapToDouble(PeriodoCredito::getCuota).sum();
    }

    public double totalInteres() {
        return periodos.stream().mapToDouble(PeriodoCredito::getInteres).sum();
    }

    public double totalAmortizado() {
        return periodos.stream().mapToDouble(PeriodoCredito::getAmortizacion).sum();
    }
}