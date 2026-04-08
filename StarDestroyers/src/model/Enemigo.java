package model;

public class Enemigo extends PiezaAbs{
	private Composite forma;
	public Enemigo(Composite pForma)
	{
		this.forma = pForma;
	}
	public void dibujar() 
	{
		forma.crear();
	}
}
