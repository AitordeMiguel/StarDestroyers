package model;

public class Enemigo extends PiezaAbs{
	private Composite forma;
	public Enemigo(Composite pForma)
	{
		this.forma = pForma;
	}
	public void dibujar() 
	{
		forma.crear(2,0);//Crear enem
	}
	public boolean encontrar(int x, int y)
	{
		return forma.encontrar(x,y);
	}
	public void borrar()
	{
		forma.borrar();
	}
}
