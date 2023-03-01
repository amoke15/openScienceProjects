package anLexico;
import java.util.*;

public class Token {
    String atributo;
    String valor;
    String lexemaSintactico;

    
    public Token (String at ,String val){
    	this.atributo = at;
    	this.valor = val;
    }
    
    public String getValue() {
    	return this.valor;
    }
    
    public String getAtributo() {
    	return this.atributo;
    }
    
    public String getLexSintactico() {
    	return this.lexemaSintactico; 
    }
    
    public void setValue(String value) {
    	this.valor = value;
    }
    
    public void setAtributo(String at) {
    	this.atributo = at;
    }
    
    public void setLexSintactico(String lex) {
    	 this.lexemaSintactico = lex; 
    }
 
    
    public String genToken() {
    	return " < " + atributo + " , " + valor + " > ";
    }
    
    public boolean esPalabraReservada(String palabra) {
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
    		if(palabra.equals(palabrasReservadas.get(i))) {
    			sol = true;
    		}
    	}
    	
    	return sol;
    }
}

    		
    
    
    
    

