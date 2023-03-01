package anLexico;

import java.util.ArrayList;


public class TablaIntermedia {

	int posTS;

	ArrayList<Token> aux = new ArrayList<Token>();
	ArrayList<String> palabrasReservadas = new ArrayList<String>();
	
	

	public TablaIntermedia(ArrayList<Token> aux) {
		this.aux = aux;
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
		
	}

	public void filtradoTIntermedia() {
		for (int i = 0; i < aux.size(); i++) {
			Token token = aux.get(i);
			if (token.getAtributo().equals("Entero")) {
				token.setLexSintactico("Entero");
			} else if (esPalabraReservada(token.getAtributo())) {
				String lex = token.getAtributo();
				int posPr = posPr(lex);
				token.setValue(String.valueOf(posPr));
				token.setAtributo("PR");
				token.setLexSintactico(lex);

			} else if (aux.get(i).getAtributo().equals("coma")) {
				token.setLexSintactico(",");
			} else if (aux.get(i).getAtributo().equals("puntoComa")) {
				token.setLexSintactico(";");
			} else if (aux.get(i).getAtributo().equals("abrirParentesis")) {
				token.setLexSintactico("(");
			} else if (aux.get(i).getAtributo().equals("cerrarParentesis")) {
				token.setLexSintactico(")");
			} else if (aux.get(i).getAtributo().equals("abrirLlaves")) {
				token.setLexSintactico("{");
			} else if (aux.get(i).getAtributo().equals("cerrarLlaves")) {
				token.setLexSintactico("}");
			} else if (aux.get(i).getAtributo().equals("operadorResta")) {
				token.setLexSintactico("-");
			} else if (aux.get(i).getAtributo().equals("operadorMenor")) {
				token.setLexSintactico("<");
			} else if (aux.get(i).getAtributo().equals("operadorMayor")) {
				token.setLexSintactico(">");
			} else if (aux.get(i).getAtributo().equals("operacionAsignacion")) {
				token.setLexSintactico("=");
			} else if (aux.get(i).getAtributo().equals("Incremento")) {
				token.setLexSintactico("++");
			} else if (aux.get(i).getAtributo().equals("operadorSuma")) {
				token.setLexSintactico("+");
			}

			else if (aux.get(i).getAtributo().equals("operadorAnd")) {
				token.setLexSintactico("&&");
			} else if (token.getAtributo().equals("operacionAsignacionLogico")) {
				token.setLexSintactico("&=");
			} else {
				String lex = token.getAtributo();
				String contenida = contenidoEnIntermedia(lex);
				if(contenida=="-1") {
					String posTString = Integer.toString(posTS);
					token.setValue(posTString);
					token.setLexSintactico("id");
					posTS++;
				}
				else {
					token.setValue(contenida);
					token.setLexSintactico("id");
					
				}
			}
		
	}
		
		
	}
	
	
	
	public ArrayList<Token> getAux() {
		return this.aux;
	}


	
	
	public String contenidoEnIntermedia(String lex) {
		String posLex = "-1";
		for(int i=0;i<aux.size();i++) {
			Token token = aux.get(i);
			if(token.getAtributo().equals(lex)) {
				if(token.getLexSintactico()!=null) {
					posLex=token.getValue();
				}
				else {
					break;
				}
			}

		}
		return posLex;
	}

	public int posPr(String lex) {
		int pos = -1;
		for (int i = 0; i < palabrasReservadas.size(); i++) {
			String pal = palabrasReservadas.get(i);
			if (pal.equals(lex)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	public boolean esPalabraReservada(String lex) {
		boolean sol = false;
		for (int i = 0; i < palabrasReservadas.size(); i++) {
			if (palabrasReservadas.get(i).equals(lex)) {
				sol = true;
				break;
			}
		}
		return sol;
	}

	



	public int getPosTS() {
		return this.posTS;
	}
	
	// for(int i = 0;i++;i<7) {
	
	public static void main (String [] args) {
		ArrayList<Token> arr = new ArrayList<Token>();
		Token tok1 = new Token("for","");
		Token tok2 = new Token("abrirParentesis","-");
		Token tok3 = new Token("int","");
		Token tok4 = new Token("i","");
		Token tok5 = new Token("operacionAsignacion","-");
		Token tok6 = new Token("Entero","0");
		Token tok7 = new Token("puntoComa","-");
		Token tok8 = new Token("i","");
		Token tok9 = new Token("Incremento","-");
		Token tok10= new Token("puntoComa","-");
		Token tok11 = new Token("i","");
		Token tok12 = new Token("operadorMenor","-");
		Token tok13 = new Token("Entero","7");
		Token tok14 = new Token("cerrarParentesis","-");
		Token tok15 = new Token("abrirLlaves","-");
		
		arr.add(tok1);
		arr.add(tok2);
		arr.add(tok3);
		arr.add(tok4);
		arr.add(tok5);
		arr.add(tok6);
		arr.add(tok7);
		arr.add(tok8);
		arr.add(tok9);
		arr.add(tok10);
		arr.add(tok11);
		arr.add(tok12);
		arr.add(tok13);
		arr.add(tok14);
		arr.add(tok15);
		
		
		TablaIntermedia tablaInter = new TablaIntermedia(arr);
		
		tablaInter.filtradoTIntermedia();
		
		ArrayList<Token> intermedia = tablaInter.getAux();
		
		for(int i=0;i<intermedia.size();i++) {
			System.out.println("enMatriz : "+ intermedia.get(i).lexemaSintactico + "        Atributo: " + intermedia.get(i).getAtributo()
					 + "        Value: " + intermedia.get(i).getValue());
		}
		
		
		
		
	}

}
