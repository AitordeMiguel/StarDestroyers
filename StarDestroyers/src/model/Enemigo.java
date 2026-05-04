package model;

public class Enemigo extends PiezaAbs{
	//private Composite forma;
	public Enemigo(Composite pForma)
	{
		super(pForma);
	}
	public boolean encontrar(int x, int y)//Llamado por removeEnem de LE
	{
		return forma.encontrar(x,y);
	}
	/*  Ya lo hereda
	public void mover(String dir)
	{
		forma.mover(dir);
	}
	*/
}
