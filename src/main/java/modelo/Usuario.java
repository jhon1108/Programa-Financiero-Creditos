package modelo;

public class Usuario {
    private String nombre;
    private double ingresos;
    private double gastos;
    private double deudas;

    public Usuario(String nombre,double ingresos, double gastos, double deudas){

      this.nombre=nombre;
      this.gastos =gastos;
      this.deudas=deudas;
      this.ingresos= ingresos;}

    public double getIngresos() {
        return ingresos;
    }

    public String getNombre(){
        return nombre;}
    public double getGastos() {
        return gastos;}
    public double getDeudas(){
        return deudas;}
    public double capacidadMensual() {
        return ingresos - gastos - deudas;
    }

    public boolean puedePagar(double cuota) {
        return cuota <= capacidadMensual();
    }
    }

