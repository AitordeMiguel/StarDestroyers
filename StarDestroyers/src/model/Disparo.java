package model;

public class Disparo extends Casilla{
	private String tipo;
	//private int[] pos; ?????
	public Disparo(String t)
	{
		tipo=t;
	}
	public String getTipo() {return tipo;}
}
