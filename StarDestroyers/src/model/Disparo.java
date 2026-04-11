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
	public boolean mover()
	{
		boolean rdo = false;//Será true si se debe borrar de la lista, pues no queda ningún pixel
		forma.mover("up");
		if (forma.tamRestante()==0) rdo=true;
		return rdo;
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
	public void notificar()
	{
		forma.notificar(1, 2, null, null);
	}
}
