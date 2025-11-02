package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivoCSSV {

    public static void guardarTablaComoCSV(List<ResultadoPeriodico> resultados, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.append("Periodo,Cuota,Interés,Amortización,Saldo,dS/Tasa,dS/Capital,dS/Tiempo\n");

            for (ResultadoPeriodico r : resultados) {
                writer.append(String.format("%d,%.2f,%.2f,%.2f,%.2f,%.5f,%.5f,%.5f\n",
                        r.getPeriodo(),
                        r.getCuota(),
                        r.getInteres(),
                        r.getAmortizacion(),
                        r.getSaldo(),
                        r.getDerivadaTiempo(),
                        r.getDerivadaCapital(),
                        r.getDerivadaTasa()
                ));
            }

            System.out.println("✅ Archivo CSV generado correctamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo CSV:");
            e.printStackTrace(); // Esto imprime el error completo
        }
    }
}