package modelos;

public class Usuario {
    private String nombre;
    private double ingresos;
    private double gastos;
    private double deudas;
    private double capacidadEndeudamiento;
    private double cuotaMaxima;

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
    public double calcularCE(){
        double ingresoDisponible =ingresos-gastos-deudas;
        return ingresoDisponible*0.4; }
    public double calcularCM(){
        return calcularCE();
    }
    }

