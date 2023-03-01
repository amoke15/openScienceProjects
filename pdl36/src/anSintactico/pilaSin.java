package anSintactico;

import java.util.*;

//pop == saca el elemento de arriba
//push == 
public class pilaSin {
	Stack<String> pila;
	String data; // dato al que aspiramos

	public pilaSin() {
		this.pila = new Stack<String>();
		pila.push("$"); // cargo el signo del dolar que es el final
		pila.push("S"); // cargo el fin
	}


	public boolean esFinal() {
		if (pila.peek() == "$") { // peek enseña el metodo de arriba
			return true;
		} else {
			return false;
		}
	}

	public String getTop() {
		return pila.peek();
	}

	public void POP() {
		pila.pop();
	}

	public void pusheamos(String cad) {
		int tam = cad.length();
		String lex = "";
		ArrayList<String> caracteresPush = new ArrayList<String>();
		for (int i = 0; i < tam; i++) {
			char caracter = cad.charAt(i);
			String car = String.valueOf(caracter);
			if (car != " ") {
				lex = lex + car;
			} else {
				caracteresPush.add(lex);
				lex = "";
			}
		}

		for (int o = caracteresPush.size() - 1; o >= 0; o--) {
			pila.push(caracteresPush.get(o));
		}
	}

}
