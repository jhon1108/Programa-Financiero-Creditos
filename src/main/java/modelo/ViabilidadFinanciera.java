package modelo;

public class ViabilidadFinanciera {

    private double ce;
    private double cmp;
    private String rango;

    public ViabilidadFinanciera(Usuario usuario) {
        this.cmp = usuario.getIngresos() - usuario.getGastos() * 0.35;
        this.ce = ((usuario.getGastos() + usuario.getDeudas()) / usuario.getIngresos()) * 100;
        if(ce < 30) {
            this.rango = "Alta probabilidad" ;
        }else{
            this.rango = "Baja probabilidad";
        }

    }
    public double getCe() {
        return ce;
    }

    public double getCmp() {
        return cmp;
    }

    public String getRango() {
        return rango;
    }
}


