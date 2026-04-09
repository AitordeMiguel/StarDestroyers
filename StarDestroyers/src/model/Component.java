package model;

public interface Component {
	boolean comprobarMover(String dir);
	boolean comprobarCrear();
	void mover(String dir);
	void crear(int tipo, int accion);
	void borrar();
	boolean encontrar(int x, int y);
	int[] getCoor();
	
}
