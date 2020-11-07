import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

class Nodo {
	
	private Nodo nodoAnterior;
	private Nodo nodoSiguiente;
	private int dato;

	public Nodo(int dato) {
		nodoAnterior = nodoSiguiente = null; // Opcional
		this.dato = dato;
	}

	public Nodo getNodoAnterior() {
		return nodoAnterior;
	}

	public void setNodoAnterior(Nodo nodoAnterior) {
		this.nodoAnterior = nodoAnterior;
	}

	public Nodo getNodoSiguiente() {
		return nodoSiguiente;
	}

	public void setNodoSiguiente(Nodo nodoSiguiente) {
		this.nodoSiguiente = nodoSiguiente;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	@Override
	public String toString() {
		return "Nodo [nodoAnterior=" + nodoAnterior + ", nodoSiguiente=" + nodoSiguiente + ", dato=" + dato + "]";
	}
	
}

/*
 * OPERACIONES BASICAS
 *   1) Creacion
 *   2) Insercion
 *     2a)Al inicio
 *     2b)Al final
 * */

class ListaDoblementeEnlazada{
	
	private Nodo nodoInicio;
	private Nodo nodoFin;
	
	// 1) Creacion
	public ListaDoblementeEnlazada() {}
	public boolean verificarVacia() {
		if(nodoInicio==null) {
			return true;
		}else {
			return false;
		}
	}

	// 2) Insertar/Agregar elemento al INICIO
	public void agregarAlInicio(int dato) {
		Nodo nuevo = new Nodo(dato);
		
		if(nodoInicio==null) {
			nodoInicio = nodoFin = nuevo;
		}else {
			nuevo.setNodoSiguiente(nodoInicio);
			nodoFin.setNodoAnterior(nuevo);
			nodoInicio = nuevo;
		}
	}
	public void agregarElementoFinal(int dato) {
		Nodo nuevo = new Nodo(dato);
		
		if(verificarVacia()) {
			nodoInicio = nodoFin = nuevo;
		}else {
			nodoFin.setNodoSiguiente(nuevo);
			nuevo.setNodoAnterior(nodoFin);
			nodoFin = nuevo;
		}
	}
	public void eliminarElementoInicio() {
		if(verificarVacia()) {
			System.out.println("No se puede eliminar lista Vacia");
		}else if(nodoInicio==nodoFin) {
			nodoInicio=nodoFin=null;
		}else {
			Nodo nodoAuxiliar=nodoInicio;
			System.out.println("Se elimino el dato: "+nodoAuxiliar.getDato());
			nodoInicio=nodoInicio.getNodoSiguiente();
			nodoAuxiliar=null;
		}
	}
	public void eliminarElementoFinal() {
		if(verificarVacia()) {
			System.out.println("No se puede eliminar lista Vacia");
		}else if(nodoInicio==nodoFin) {
			System.out.println("El dato eliminado es: "+nodoFin.getDato());
			nodoInicio=nodoFin=null;
		}else {
			System.out.println("El dato eliminado es: "+nodoFin.getDato());
			nodoFin = nodoFin.getNodoAnterior();
			nodoFin.setNodoSiguiente(null);
		}
	}
	public void eliminarDeterminadoElemento(int dato) {
		if(verificarVacia()) {
			System.out.println("Oh no se puede eliminar el dato ya que la lista esta vacia");
		}else if(nodoInicio==nodoFin && nodoInicio.getDato()==dato) {
			System.out.println("Se elimino el dato");
			nodoInicio=nodoFin=null;
		}else {
			Nodo adelante = nodoInicio;
			Nodo anterior = null;
			
			while(adelante!=null) {
				if(adelante.getDato()!=dato) {
					anterior=adelante;
					adelante=adelante.getNodoSiguiente();
				}else {
					break;
				}
			}
			
			if(adelante!=null) {
				adelante.getNodoSiguiente().setNodoAnterior(anterior);
				anterior.setNodoSiguiente(adelante.getNodoSiguiente());
				System.out.println("Se elimino el dato");
			}
			
		}
		
}
	
	public void mostrarElementos() {
		Nodo nodoActual = nodoInicio;
		
		while(nodoActual != null) {
			System.out.print("<--["+nodoActual.getDato()+"]-->");
			nodoActual = nodoActual.getNodoSiguiente();
		}
	}
}

public class Prueba {

	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		ListaDoblementeEnlazada lista=new ListaDoblementeEnlazada();
		String op="";
		lista.agregarAlInicio(10);
		lista.agregarAlInicio(11);
		lista.agregarAlInicio(13);
		do {
			System.out.println("Elige una opcion");
			System.out.println("A) Agregar elemento");
			System.out.println("B) Eliminar elemento");
			System.out.println("C) Mostrar Lista");
			System.out.println("D) Salir");
			op=entrada.nextLine().toUpperCase();
			switch (op) {
			case "A":
				System.out.print("Ingresa el dato: ");
				int dato=entrada.nextInt();
				System.out.println();
				String op2="";
				do {
					entrada.nextLine();
					System.out.println("A) Agregar al inicio");
					System.out.println("B) Agregar al final");
					System.out.println("C) Regresar al menu principal");
					op2=entrada.nextLine().toUpperCase();
					
					if(op2.equalsIgnoreCase("A")) {
						lista.agregarAlInicio(dato);
					}else if(op2.equalsIgnoreCase("B")) {
						lista.agregarElementoFinal(dato);
					}else if(op2.equalsIgnoreCase("C")) {
						break;
					}else {
						System.out.println("Elge una opcion disponible");
					}
				}while(!(op2.equalsIgnoreCase("A")|| op2.equalsIgnoreCase("B") || op2.equalsIgnoreCase("C")));
				break;
			case "B":
				String opB="";
				do {
					System.out.println("A) Eliminar al inicio");
					System.out.println("B) Eliminar al final");
					System.out.println("C) Eliminar un determinado elemento ");
					System.out.println("D) Regresar al menu principal");
					opB=entrada.nextLine();
					
					if(opB.equalsIgnoreCase("A")) {
						lista.eliminarElementoInicio();
					}else if(opB.equalsIgnoreCase("B")) {
						lista.eliminarElementoFinal();
					}else if(opB.equalsIgnoreCase("C")) {
						System.out.print("Ingresa el dato que deseas eliminar: ");
						int d=entrada.nextInt();
						entrada.nextLine();
						lista.eliminarDeterminadoElemento(d);
					}else if(opB.equalsIgnoreCase("D")) {
						break;
					}else {
						System.out.println("Elge una opcion disponible");
					}
				}while(!(opB.equalsIgnoreCase("A")|| opB.equalsIgnoreCase("B") || opB.equalsIgnoreCase("C")));
				break;
			case "C":
				lista.mostrarElementos();
				System.out.println();
				break;
			case "D":
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Porfavor elige una opcion disponible");
				break;
			}
		}while(!op.equalsIgnoreCase("D"));
		
	}

}

