package model;

public class Enemigo extends PiezaAbs{
	private Composite forma;
	public Enemigo(Composite pForma)
	{
		this.forma = pForma;
	}
	public void crear()//Método de la inicialización
	{
		forma.crear(0);//0 indica inicializar
	}
	public boolean encontrar(int x, int y)//Llamado por removeEnem de LE
	{
		return forma.encontrar(x,y);
	}
	public void borrar()//Llamado por removeEnem de LE
	{
		forma.borrar();
	}
	public void mover()
	{
		forma.mover("down");
	}
}
