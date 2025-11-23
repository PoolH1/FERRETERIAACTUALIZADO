package Arreglo;

import java.util.ArrayList;
import Clase.Venta;

public class ArregloVentas {
    private ArrayList<Venta> ventas;

    public ArregloVentas() {
        ventas = new ArrayList<>();
    }

    public void adicionar(Venta v) {
        ventas.add(v);
    }

    public Venta buscar(int id) {
        for (Venta v : ventas) {
            if (v.getIden() == id) {
                return v;
            }
        }
        return null;
    }

    public void eliminar(Venta v) {
        ventas.remove(v);
    }

    public int tamaño() {
        return ventas.size();
    }

    public Venta obtener(int i) {
        return ventas.get(i);
    }
}