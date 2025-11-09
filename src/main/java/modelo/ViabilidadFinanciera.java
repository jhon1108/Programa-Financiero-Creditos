package modelo;

public class ViabilidadFinanciera {

    private Double ce;
    private Double cmp;
    private String rango;

    public ViabilidadFinanciera(Usuario usuario) {

        double ingresos = usuario.getIngresos();
        double gastos   = usuario.getGastos();
        double deudas   = usuario.getDeudas();


        this.cmp = ingresos - gastos - deudas;


        this.ce = ingresos == 0 ? 100.0 : ((gastos + deudas) / ingresos) * 100.0;


        if (ce <= 30) {
            this.rango = "Excelente";
        } else if (ce <= 40) {
            this.rango = "Aceptable";
        } else if (ce <= 60) {
            this.rango = "Riesgoso";
        } else {
            this.rango = "No viable";
        }
    }

    public Double getCe() { return ce; }
    public Double getCmp() { return cmp; }
    public String getRango() { return rango; }
}
