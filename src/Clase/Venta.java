package Clase;

public class Venta {
    private int iden;
    private String nombre;
    private String prod;
    private int cant;
    private double preuni;

    public Venta(int iden, String nombre, String prod, int cant, double preuni) {
        this.iden = iden;
        this.nombre = nombre;
        this.prod = prod;
        this.cant = cant;
        this.preuni = preuni;
    }

    public int getIden() { return iden; }
    public void setIden(int iden) { this.iden = iden; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProd() { return prod; }
    public void setProd(String prod) { this.prod = prod; }

    public int getCant() { return cant; }
    public void setCant(int cant) { this.cant = cant; }

    public double getPreuni() { return preuni; }
    public void setPreuni(double preuni) { this.preuni = preuni; }

    public double total() {
        return cant * preuni;
    }

   
}
