package modelo;
import java.util.List;


public class ArchivoCSV {


    public String generarCSV(List<PeriodoCredito> listaPeriodos) {
        StringBuilder sb = new StringBuilder();

        sb.append("Periodo (t),Cuota a(t),Amortización A(t),Interés I(t),Saldo S(t),")
                .append("Δa_t',ΔA_t',ΔI_t',ΔS_t',")
                .append("Sensibilidad dS/dTasa,Sensibilidad dS/dCapital,Sensibilidad dS/dTiempo\n");


        for (PeriodoCredito p : listaPeriodos) {
            sb.append(p.getT()).append(",")
                    .append(String.format("%.2f", p.getA_t())).append(",")
                    .append(String.format("%.2f", p.getA_t_acum())).append(",")
                    .append(String.format("%.2f", p.getI_t())).append(",")
                    .append(String.format("%.2f", p.getS_t())).append(",")
                    .append(String.format("%.2f", p.getA_t_prime())).append(",")
                    .append(String.format("%.2f", p.getA_t_acum_prime())).append(",")
                    .append(String.format("%.2f", p.getI_t_prime())).append(",")
                    .append(String.format("%.2f", p.getS_t_prime())).append(",")
                    .append(String.format("%.6f", p.getDsdTasa())).append(",")
                    .append(String.format("%.6f", p.getDsdCapital())).append(",")
                    .append(String.format("%.6f", p.getDsdTiempo()))
                    .append("\n");
        }

        return sb.toString();
    }
}