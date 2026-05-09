package model;

import java.util.Random;

public class Enemigo extends PiezaAbs{
	private int vidas;
	public Enemigo(Composite pForma)
	{
		super(pForma);
		vidas = new Random().nextInt(1,3);//Puede tener de 1 a 10 vidas  
		if(vidas>5 && vidas<10) vidas=5;//de 5 a 10 no hay
		//vidas = vidas*2 +1;
	}
	public boolean encontrar(int x, int y)//Llamado por removeEnem de LE
	{
		return forma.encontrar(x,y);
	}
	public boolean recibirDisp()
	{
		boolean rdo = false;
		vidas--;
		if(vidas==0) rdo = true;
		return rdo;
	}
}
