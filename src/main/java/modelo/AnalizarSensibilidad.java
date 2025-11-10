package modelo;

public class AnalizarSensibilidad {

    private final Credito credito;

    public AnalizarSensibilidad(Credito credito) {
        this.credito = credito;
    }

    public double sensibilidadCapital() {
        return credito.sensibilidadRespectoCapital(credito.getMetodoAmortizacion().getNumeroCuotas()) ;
    }
    public double sensibilidadTasa() {
        return credito.sensibilidadRespectoTasa(credito.getMetodoAmortizacion().getNumeroCuotas()) ;
    }
    public double sensibilidadTiempo() {
        return credito.sensibilidadRespectoTiempo(credito.getMetodoAmortizacion().getNumeroCuotas()) ;
    }
    

}
