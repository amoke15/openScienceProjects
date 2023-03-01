package anLexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import anSintactico.TablaAnS;
import anSintactico.pilaSin;
import anLexico.*;
public class Lexer {
	
	   public static TablaAnS tablaGo = new TablaAnS();
	    
	   
		public static void main(String[] args) throws IOException{
				
		File f = new File("C:\\Users\\adrio\\eclipse-workspace\\pdl36\\src\\anLexico\\pruebaLector.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Character> lex = new ArrayList<Character>();
		int c = 0;
		char car;
		String palabra;
		Token t = new Token(null,null);
		ArrayList<Token> tokens = new ArrayList<Token>();
	    TablaSimbolos tablaSimb = new TablaSimbolos(tokens);
		Transicion tr = new Transicion(0,"");
		Error error = new Error(50);
		
		while((c = br.read())!=-1) { //Leemos hasta el final del fichero
			car = (char) c;
			String leido = String.valueOf(car); 
			//Comparamos si el primer caracter leido nos genera un token o nos lleva a otra transicion
//			PRIMERA TRANSICION
			switch(leido) {
			case "," :
				tr.setEstado(1);
				tr.setAccion("A");
				t = new Token("coma","-");
                tablaSimb.anadirAux(t);
				break;
			case ";" :
				t = new Token("puntoComa","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(2);
				tr.setAccion("B");
				break;
			case "(" :
				t = new Token("abrirParentesis","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(3);
				tr.setAccion("C");
				break;
			case ")" :
				t = new Token("cerrarParentesis","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(4);
				tr.setAccion("D");
				break;
			case "{" :
				t = new Token("abrirLlaves","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(5);
				tr.setAccion("E");
				break;
			case "}" :
				t = new Token("cerrarLlaves","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(6);
				tr.setAccion("F");
				break;
			case "-" :
				t = new Token("operadorResta","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(7);
				tr.setAccion("G");
				break;
			case "+" :
				tr.setEstado(8);
				tr.setAccion("H");
				break;
			case ">" :
				t = new Token("operadorMayor","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(11);
				tr.setAccion("J");
				break;
			case "<" :
				t = new Token("operadorMenor","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(12);
				tr.setAccion("K");
				break;
			case "=" :
				t = new Token("operacionAsignacion","-");
				tablaSimb.anadirAux(t);
				tr.setEstado(13);
				tr.setAccion("L");
				break;
			case "&" :
				tr.setEstado(14);
				tr.setAccion("M");
				break;
			case " ":
				break;
				default :
					if(Character.isDigit(car)) {
						lex.add(car);
						tr.setEstado(19);
						tr.setAccion("R");
						break;
					}else if(Character.isLetter(car)) {
						lex.add(car);
						tr.setEstado(17);
						tr.setAccion("P");
						break;
					}else if(leido.equals("_")) {
						lex.add(car);
						tr.setEstado(21);
						tr.setAccion("T");
						break;
					}
					
//			case " " " :
//				tr.setEstado(27);
//				tr.setAccion("Y");
				}
			boolean esFin = tr.esFinal(); //Tras la primera iteracion vemos si es estado final o una nueva transicion
			boolean añadido = false;				//			SEGUNDA TRANSICION//	Leemos siguiente caracter
			while(!esFin && c != -1 && añadido==false){//Mientras no sea fin de fichero y estemos en transiciones aun..-
				c = br.read(); 
				esFin = tr.esFinal(); 
				car = (char) c;
				leido = String.valueOf(car);
				int estado = tr.getEstado();
				
				switch(estado) { //Dependiendo de un estado u otro tenemos diferentes acciones semanticas
				case 8 :
					if(leido.equals("+")){
						t = new Token("Incremento","-");
						tablaSimb.anadirAux(t);
						añadido = true;
						tr.setAccion("I");
						tr.setEstado(9);
					}else {
						t = new Token("operadorSuma","-");
						tablaSimb.anadirAux(t);
						añadido = true;
						tr.setAccion("o.c");
						tr.setEstado(10);
					}
					break;
				case 14 :
					if(leido.equals("&")) {
						t = new Token("operadorAnd","-");
						tablaSimb.anadirAux(t);
						añadido = true;
					}else if(leido.equals("=")) {
						t = new Token("operadorAsignacionLogico","-");
						tablaSimb.anadirAux(t);
					}else {
						error.setNumError(50);
						error.error();
					}
					break;
				case 17 :
					if(Character.isLetter(car) || leido.equals("_") || Character.isDigit(car) ) {
						lex.add(car);
					}
					else {
						if(lex.size()>64) {
							error.setNumError(54);
							error.error();
						}
						else {
							palabra = cadLeida(lex);
							lex.clear();
							t = new Token(palabra,"");
							tablaSimb.anadirAux(t);
							añadido = true;
							
						}
					}
					break;
				case 19 :
					if(Character.isDigit(car)) {
						lex.add(car);
					}else {
						palabra = cadLeida(lex);
						if(Integer.valueOf(palabra)>32767) {
							error.setNumError(53);
							error.error();
						}
						else {
							t = new Token("Entero",palabra);
							tablaSimb.anadirAux(t);
							añadido = true;
						}
					}
					break;
				case 21 :
					if(leido.equals("[0-9]") || leido.equals("_") || leido.equals("[a-zA-Z]")) {
						lex.add(car);
					}else {
						if(lex.size()>64) {
							error.setNumError(54);
							error.error();
						}
						else {
							palabra = cadLeida(lex);
							lex.clear();
							t = new Token(palabra,"");
							tablaSimb.anadirAux(t);
							añadido = true;
						}
						
					}
					break;
					default:
						break;
				}
				
			} // Hemos llegado a final de fichero con nuestra lista de tokens generada luego la imprimimos
		
				
				
		} 
		
		br.close();
		

		System.out.println("A continuacion los tokens a printear en el fichero de salida");
		System.out.println("El value esta vacio ya que se rellena en la tabla intermedia (posPR y posTS) ");
		
		for(int i=0;i<tokens.size();i++) {
			System.out.print(tokens.get(i).genToken());
			System.out.println("");
		}
		
		System.out.println("-----------------------------------------------------------------------------------");
		
		System.out.println("Tabla intermedia --> Values adjudicados : los ID`s ya tienen su posTS y las PR`s su posPR");
		System.out.println("");
		System.out.println("");
			
		TablaIntermedia TI = new TablaIntermedia(tokens);
		
		
		
		TI.filtradoTIntermedia();
		
		tablaSimb.filtradoTS(TI.aux);
		
		
	
        
		FileWriter fw = new FileWriter("C:\\Users\\adrio\\eclipse-workspace\\pdl36\\src\\anLexico\\FicheroSalida.txt");
		
		for(int i=0;i<TI.aux.size();i++) {
			Token tok = TI.aux.get(i);
			fw.write(tok.genToken());
			System.out.println(tok.lexemaSintactico + " "  + "<" + tok.getAtributo() + " , " + tok.getValue()+ ">");
		}
		
		
		fw.close();
		
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------------------------------------------------------------");
		
		System.out.println("A continuacion la tabla de simbolos, que contiene filas, pero printeamos solo el lexema y la posiciÃ³n en la tabla de simbolos (por ahora)");
			
		System.out.println("el objeto Fila tiene tb el parametro token para poder acceder a esa posicion");
		System.out.println("");
		System.out.println("");
		
		for(int i=0;i<tablaSimb.TS.size();i++) {
        	System.out.println(tablaSimb.TS.get(i).getLexema() + " , " + tablaSimb.TS.get(i).getToken().getValue());
        }
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		
//		System.out.println("Comprobacion de que el lexema a buscar en el sintactico es correcto : ");
//		
//		for(int i=0;i<tokens.size();i++) {
//			System.out.println(aBuscarEnMatriz(tokens.get(i).atributo,tablaInter));
//		}
		


		System.out.println("");
		System.out.println("");
		System.out.println("A continuacion el resultado del accept del analisis sintactico");
		System.out.println("");
		System.out.println("");
		
		
		TablaAnS tablaGo = new TablaAnS();
		pilaSin pila = new pilaSin();

		
		
		boolean aceptado = accept(tokens,pila,tablaGo);
		if(aceptado) {
			System.out.println("Sintaxis correcta");
		}
		
		
	}



		
		public static String cadLeida(ArrayList<Character> lex) {
			String lexema ="";
			for(int i = 0; i < lex.size(); i ++) {
				char c = lex.get(i);
				lexema = lexema + String.valueOf(c);
			}
			return lexema;
		}
		
		

		
		
		public static boolean accept(ArrayList<Token> array ,pilaSin pila,TablaAnS tablaGo) {
			boolean aceptado = true;
			for(int i=0;i<array.size();i++) {
				String elemTop = pila.getTop().toString();
				String tokElem =array.get(i).getLexSintactico();
				if(tablaGo.esTerminal(elemTop)) {
					if(tokElem.equals(elemTop)) {
						pila.POP();
					}
					else {
						System.out.println("Error Sintactico");
						aceptado=false;
						break;
					}
				}
				else {
					int posNoTerminal = tablaGo.getMap().get(elemTop);
					buscaEnMatriz(pila,posNoTerminal,tokElem,tablaGo.getMatriz());
				}
				if(pila.esFinal()) {
					aceptado=false;
				}
			}
			return aceptado;
			
		}
		
		
		
		public static boolean esPalabraReservada(String lex) {
	    	boolean sol=false;
	    	ArrayList<String> palabrasReservadas = new ArrayList<String>();
	    	palabrasReservadas.add("boolean");
	    	palabrasReservadas.add("for");
	    	palabrasReservadas.add("function");
	    	palabrasReservadas.add("if");
	    	palabrasReservadas.add("while");
	    	palabrasReservadas.add("let");
	    	palabrasReservadas.add("int");
	    	palabrasReservadas.add("true");
	    	palabrasReservadas.add("false");
	    	palabrasReservadas.add("return");
	    	for (int i = 0 ; i < palabrasReservadas.size(); i++) {
	    		if(palabrasReservadas.get(i).equals(lex)) {
	    			sol = true;
	    		}    		
	    	}
	    	return sol;
	    }
		
		
		public static void buscaEnMatriz(pilaSin pila,int posNoTerminal,String lex,String [][] tablaAn) {
			int posColumna = tablaGo.getMapColum().get(lex);
			String regla = tablaAn[posNoTerminal][posColumna];
		    if(regla==null) {
		    	System.out.println("Error Sintactico");
		    }
		    else {
		    	pila.POP();
		    	pila.pusheamos(regla);
		    	
		    }
		}
					
}
	
