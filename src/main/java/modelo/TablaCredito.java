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
            double sAnt = periodos.get(i - 1).getS_t();
            double sAct = periodos.get(i).getS_t();
            double reduccion = sAnt - sAct;

            if (reduccion > maxReduccion) {
                maxReduccion = reduccion;
                periodoClave = periodos.get(i).getT();
            }
        }
        return periodoClave;
    }

    public int periodoMayorInteres() {
        double maxInteres = 0;
        int periodoClave = 1;

        for (PeriodoCredito p : periodos) {
            if (p.getI_t() > maxInteres) {
                maxInteres = p.getI_t();
                periodoClave = p.getT();
            }
        }
        return periodoClave;
    }

    public double totalPagado() {
        return periodos.stream()
                .mapToDouble(pc -> pc.getA_t() + pc.getI_t())
                .sum();
    }

    public double totalInteres() {
        return periodos.stream()
                .mapToDouble(PeriodoCredito::getI_t)
                .sum();
    }

    public double totalAmortizado() {
        return periodos.stream()
                .mapToDouble(PeriodoCredito::getA_t)
                .sum();
    }
}
