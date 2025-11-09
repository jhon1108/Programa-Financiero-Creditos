package modelo;


public class PeriodoCredito {
    private final int t;


    private final double a_t;
    private final double A_t;
    private final double I_t;
    private final double S_t;

    private final double a_t_prime;
    private final double A_t_prime;
    private final double I_t_prime;
    private final double S_t_prime;


    private final double dS_dTasa;
    private final double dS_dCapital;
    private final double dS_dTiempo;

    public PeriodoCredito(int t, double a_t, double A_t, double I_t, double S_t, double a_t_prime, double A_t_prime, double I_t_prime, double S_t_prime, double dS_dTasa, double dS_dCapital, double dS_dTiempo) {
        this.t = t;
        this.a_t = a_t;
        this.A_t = A_t;
        this.I_t = I_t;
        this.S_t = S_t;
        this.a_t_prime = a_t_prime;
        this.A_t_prime = A_t_prime;
        this.I_t_prime = I_t_prime;
        this.S_t_prime = S_t_prime;
        this.dS_dTasa = dS_dTasa;
        this.dS_dCapital = dS_dCapital;
        this.dS_dTiempo = dS_dTiempo;
    }

    public int getT() { return t; }
    public double getA_t() { return a_t; }
    public double getA_t_acum() { return A_t; }
    public double getI_t() { return I_t; }
    public double getS_t() { return S_t; }

    public double getA_t_prime() { return a_t_prime; }
    public double getA_t_acum_prime() { return A_t_prime; }
    public double getI_t_prime() { return I_t_prime; }
    public double getS_t_prime() { return S_t_prime; }

    public double getDsdTasa() { return dS_dTasa; }
    public double getDsdCapital() { return dS_dCapital; }
    public double getDsdTiempo() { return dS_dTiempo; }
}
