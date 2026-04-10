package model;

public class Disparo{
	private Composite forma;
	public Disparo(Composite comp)
	{
		forma = comp;
	}
	public void dibujar()
	{
		forma.crear();//crear disparo
	}
	public void mover()
	{
		forma.mover("up");
	}
}
