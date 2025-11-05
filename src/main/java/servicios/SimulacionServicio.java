package servicios;

import modelo.*;

import java.util.List;

public class SimulacionServicio {
    public List<ResultadoPeriodico> simularCredito(Credito credito) {
        SimularCredito simulador = new SimularCredito(credito);
        return simulador.simular();
    }
}