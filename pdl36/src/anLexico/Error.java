package anLexico;

public class Error {
      
	  int numError;
      
      public Error(int nE) {
    	  this.numError = nE;
      }

	public Integer getNumError() {
		return numError;
	}

	public void setNumError(int numError) {
		this.numError = numError;
	}

	
	public void error() {
		if(numError==50) {
			System.out.println("ERROR 50 : Caracter no esperado");
		}
		else if (numError==51) {
			System.out.println("ERROR 51 : Identificador ya declarado");
		}
		else if (numError==52) {
			System.out.println("ERROR 52 : Identificador no declarado");
		}
		else if (numError==53) {
			System.out.println("ERROR 53 : Entero fuera de rango");
		}
		else if (numError==54) {
			System.out.println("ERROR 54 : Cadena superior a 128B (64 caracteres)");
		}
		else if (numError==55) {
			System.out.println("ERROR 55 : No alfa al final de una cadena");
		}
	}
        
}