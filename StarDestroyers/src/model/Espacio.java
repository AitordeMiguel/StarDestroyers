package model;

import java.util.Random;
import java.util.ArrayList;

public class Espacio {
	private Casilla[][] tablero;
	private static Espacio miEspacio;
	private Espacio(String color,ArrayList<int[]> posE)
	{
		
		tablero = new Casilla[60][100];
		for(int f=0;f<60;f++)
		{
			for(int c=0;c<100;c++)
			{
				if(!posE.isEmpty() &&f==2 && c==posE.get(0)[1])
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
	
	public static Espacio getEspacio(String color,ArrayList<int[]> posE)
	{
		if(miEspacio == null)
		{
			miEspacio = new Espacio(color,posE);
		}
		return miEspacio;
	}
}
