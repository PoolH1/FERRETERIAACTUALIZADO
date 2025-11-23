package Clase;

public class Cliente {
		
		private String nombre;
		private String dni;
		
		public Cliente(String nombre, String dni)
		{
			this.nombre = nombre;
			this.dni = dni;
		}
		
		public String getNombre() {
			return nombre;
		}
		public String getDni() {
			return dni;
		}
		
		public void comprar() {
	        System.out.println(nombre + " está realizando una compra.");
	    }
		
		public void saludar() {
		    System.out.println("Hola, soy " + nombre + " y vengo a comprar.");
		}

		public static void main(String[] args) {
			Cliente cliente1 = new Cliente("Fernando", "12345678");
			cliente1.saludar();
	        cliente1.comprar();
		}
	}

