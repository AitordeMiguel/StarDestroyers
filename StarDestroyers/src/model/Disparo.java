package model;

public class Disparo{
	private Composite forma;
	public Disparo(Composite comp)
	{
		forma = comp;
	}
	public void dibujar()
	{
		forma.crear(3,-1);//crear disparo, -1 porque no se usa
	}
}
