package anLexico;
import java.util.*;
import java.util.Map.Entry;

import anLexico.Token;
public class TablaSimbolos {
	
	int idTabla;
	ArrayList<Token> aux = new ArrayList<Token>();
	ArrayList<Fila> TS = new ArrayList<Fila>();
	ArrayList<String> palRes = new ArrayList<String>();

	
	public TablaSimbolos(ArrayList<Token> arr) {
		this.aux = arr;
	}
	
	
	public void filtradoTS(ArrayList<Token> tokens) {
		for(int i=0;i<tokens.size();i++) {
			Token token = tokens.get(i);
			String clave = token.getLexSintactico();
			if(clave.equals("id")) {
				Fila fila = new Fila(token,token.getAtributo(),null,0,0,0,0,null); //los demas parametros se modificarán mas adelante
				TS.add(fila);
			}
		}
	}
		
	public void anadirAux (Token token) {
			aux.add(token);
	}
		
	
		
	
	
	public ArrayList<Token> getAux() {
		return this.aux;
	}
	
	
	
	
	
}
