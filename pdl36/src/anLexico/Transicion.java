package anLexico;

public class Transicion {
   int estado = 0;
   String accion;
   
   public Transicion(int estado,String accion) {
	   this.estado = estado;
	   this.accion = accion;
   }
   public int getEstado() {
	   return this.estado;
   }
   public String getAccion() {
	   return this.accion;
   }
   public void setEstado(int estado) {
	   this.estado = estado;
   }
   public void setAccion(String accion) {
	   this.accion =  accion;
   }
   
   public boolean esFinal() { //los estados finales son muchos
	   boolean sol = true;//los estados no finales son: 8,14,17,19,21,23,24,25,27
	   if(estado == 8 || estado == 14 || estado == 17 || estado == 19 || estado == 21 || estado == 23 ||
			   estado == 24 || estado == 25 || estado == 27) {
		   sol = false;
	   }
	   
	   return sol;
   }
}
