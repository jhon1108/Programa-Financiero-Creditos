package servicios;
import modelo.*;

public class EvaluacionServicio {
    public ViabilidadCredito evaluarViabilidad(Usuario usuario, Credito credito) {
        return new ViabilidadCredito(usuario, credito);
    }
}