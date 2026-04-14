package model;

public interface Component {
	boolean comprobarMover(String dir);
	boolean comprobarCrear();
	boolean mover(String dir);
	void crear();//Es al inicializar
	void dibujar();//Es como el de arriba pero en partida
	void borrar();
	boolean encontrar(int x, int y);
	int[] getCoor();
	
}
