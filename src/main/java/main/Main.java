package main;

import modelo.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n==== SIMULADOR DE CRÉDITO ====\n");

        // ===== DATOS DEL CASO AMERICANO =====
        double capital = 150000;
        double tasa = 0.005;      // mensual
        int cuotas = 8;

        // crear método americano
        MetodoAmortizacion metodo =
                new MetodoAmericano(capital, tasa, cuotas);

        // crear crédito
        Credito credito = new Credito(null, metodo);

        // proyectar tabla
        ProyectarFunciones proyector = new ProyectarFunciones(credito);
        var tabla = proyector.getTabla();

        // ===== IMPRIMIR TABLA =====
        System.out.println("======== TABLA AMORTIZACIÓN ========");
        System.out.printf("%-3s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s%n",
                "t", "a(t)", "A(t)", "I(t)", "S(t)", "a'(t)", "A'(t)", "I'(t)", "S'(t)");

        System.out.println("----------------------------------------------------------------------------------------");

        for (PeriodoCredito p : tabla) {
            System.out.printf("%-3d | %-10.2f | %-10.2f | %-10.2f | %-10.2f | %-10.2f | %-10.2f | %-10.2f | %-10.2f%n",
                    p.getT(),
                    p.getA_t(),
                    p.getA_t_acum(),
                    p.getI_t(),
                    p.getS_t(),
                    p.getA_t_prime(),
                    p.getA_t_acum_prime(),
                    p.getI_t_prime(),
                    p.getS_t_prime()
            );
        }

        // ===== SENSIBILIDAD FINAL =====
        AnalizarSensibilidad analizador = new AnalizarSensibilidad(credito);
        PeriodoCredito finalP = analizador.obtenerSensibilidadFinal();

        double dTasa    = finalP.getDsdTasa();
        double dCapital = finalP.getDsdCapital();
        double dTiempo  = finalP.getDsdTiempo();
        double saldoFinal = finalP.getS_t();

        System.out.println("\n==== SENSIBILIDAD FINAL ====");
        System.out.println("Periodo final: " + finalP.getT());
        System.out.println("Saldo S(T):   " + saldoFinal);
        System.out.println("dS/dTasa:     " + dTasa);
        System.out.println("dS/dCapital:  " + dCapital);
        System.out.println("dS/dTiempo:   " + dTiempo);

        // ===== RECOMENDACIONES =====
        GenerarRecomendaciones gen = new GenerarRecomendaciones(credito);
        var recomendaciones = gen.obtenerRecomendaciones();

        System.out.println("\n==== RECOMENDACIONES ====");
        for (String r : recomendaciones) {
            System.out.println("- " + r);
        }
    }
}
