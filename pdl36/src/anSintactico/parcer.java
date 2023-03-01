package anSintactico;
import java.util.*;

public class parcer {
	pilaSin pila;
	public parcer() {
		// TODO Auto-generated constructor stub
	}
	public char[] tokenTabla(String dato){ // problema de este metodo , devuelve huecos al final 
		char[] tokens = new char[dato.length()];
		for(int i = 0 ; i<dato.length();i++) {
			if(dato.charAt(i) != ' ') { // si es espacio sudo , si no lo meto en la array
				tokens[i] = dato.charAt(i);
			}
		}
		return tokens;
	}
	
	
	/*public void cambiarPila(){
		String elemento = pila.pop().toString();
		if(elemento == " ") {
			System.out.print("error");
		}else {
			
		}*/
	

}
