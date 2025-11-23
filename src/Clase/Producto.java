package Clase;

public class Producto {
	private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public int getStock() {
        return stock;
    }

    public void vender(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
            System.out.println("Se vendieron " + cantidad + " unidades de " + nombre);
        } else {
            System.out.println("No hay suficiente stock de " + nombre);
        }
    }

    public void mostrarInfo() {
        System.out.println("Producto: " + nombre + 
                           " | Precio: " + precio + 
                           " | Stock: " + stock);
    }

    public static void main(String[] args) {
        Producto p1 = new Producto("Martillo", 35.5, 10);

        p1.mostrarInfo();
        p1.vender(3);
        p1.mostrarInfo();
        p1.vender(15); 
    }
}
