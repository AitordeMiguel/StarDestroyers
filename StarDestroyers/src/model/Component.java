package model;

public interface Component {
	boolean comprobarMover(String dir);//Llamada previa para saber si se puede
	boolean mover(String dir);//Actrualiza posiciones
	void crear(int accion);//Para dibujarlo en el tablero
	void borrar();//Al borrar un componente
	boolean encontrar(int x, int y);//Cuando se borra enem o disp
	void notificar(int dest, int estado, String color, int accion, int tipo);//Llama disparo
}
