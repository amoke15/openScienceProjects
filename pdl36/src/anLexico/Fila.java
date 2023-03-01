package anLexico;

public class Fila {
      
	 Token token;  // anadimos un parametro extra Token asociado a cada fila
	 String lexema; // para tener a mano el token y por tanto su posición en la TS
	 String tipo;
	 int desp;
	 int numParam;
	 int tipoParam;
	 int tipoDev;
	 String etiq;
	
	 public Fila(Token token,String lexema, String tipo, int desp, int numParam, int tipoParam, int tipoDev, String etiq) {
		 this.token=token;
		 this.lexema=lexema;
		 this.tipo=tipo;
		 this.desp=desp;
		 this.numParam=numParam;
		 this.tipoParam=tipoParam;
		 this.tipoDev=tipoDev;
		 this.etiq=etiq;
		 
	 }
	 
	 public Token getToken() {
		 return token;
	 }
	 public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getDesp() {
		return desp;
	}
	public void setDesp(int desp) {
		this.desp = desp;
	}
	public int getNumParam() {
		return numParam;
	}
	public void setNumParam(int numParam) {
		this.numParam = numParam;
	}
	public int getTipoParam() {
		return tipoParam;
	}
	public void setTipoParam(int tipoParam) {
		this.tipoParam = tipoParam;
	}
	public int getTipoDev() {
		return tipoDev;
	}
	public void setTipoDev(int tipoDev) {
		this.tipoDev = tipoDev;
	}
	public String getEtiq() {
		return etiq;
	}
	public void setEtiq(String etiq) {
		this.etiq = etiq;
	}
}
