package model;

import java.util.Random;
import java.util.ArrayList;

public class Espacio {
	private Casilla[][] tablero;
	public Espacio(String color)
	{
		int cantEnem = new Random().nextInt(4,9);
		ArrayList<Integer> posE = new ArrayList();
		int dist=90/cantEnem;
		for(int i=0;i<cantEnem;i++){posE.add(5+i*dist);}
		tablero = new Casilla[60][100];
		for(int f=0;f<60;f++)
		{
			for(int c=0;c<100;c++)
			{
				if(f==2 && c==posE.get(0))
				{
					posE.remove(0);
					tablero[f][c] = new Enemigo();
				}
				else if(f==55 && c==50)
				{
					tablero[f][c] = new Nave(color);
				}
				else
				{
					tablero[f][c] = new Casilla();
				}
			}
		}
	}
}
