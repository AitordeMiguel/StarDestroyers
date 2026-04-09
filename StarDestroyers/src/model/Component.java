package model;

public interface Component {
	boolean comprobarMover(String dir);
	boolean comprobarCrear();
	void mover(String dir);
	void crear();
	void borrar();
	boolean encontrar(int x, int y);
	
}
