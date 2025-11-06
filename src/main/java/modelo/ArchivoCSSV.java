package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivoCSSV {

    public static void guardarTablaComoCSV(List<ResultadoPeriodico> resultados, String rutaArchivo) throws IOException {
        if (resultados == null || resultados.isEmpty()) {
            throw new IllegalArgumentException("La lista de resultados no puede ser nula ni vacía.");
        }
        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía.");
        }

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.append("Periodo,Cuota,Interes,Amortizacion,Saldo,dS_Tasa,dS_Capital,dS_Tiempo\n");

            for (ResultadoPeriodico r : resultados) {
                writer.append(String.format("%d,%.2f,%.2f,%.2f,%.2f,%.5f,%.5f,%.5f\n",
                        r.getPeriodo(),
                        r.getCuota(),
                        r.getInteres(),
                        r.getAmortizacion(),
                        r.getSaldo(),
                        r.getDerivadaTasa(),
                        r.getDerivadaCapital(),
                        r.getDerivadaTiempo()
                ));
            }
        }
    }
}