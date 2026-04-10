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
	public boolean encontrar(int[] coor)
	{
		boolean rdo =  forma.encontrar(coor[0], coor[1]);
		if(rdo)//Si es el disparo que ha chocado
		{
			forma.borrar();//Solo los elimina del tablero
			forma.notificar(1, 2, null, null);//notificar al juego que redibuje
		}
		return rdo;
	}
}
