package model;

import java.util.Random;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

public class Espacio extends Observable{
	private Casilla[][] tablero;
	private static Espacio miEspacio;
	private Espacio()
	{
		
	}
	public void inicializar(String color,ArrayList<int[]> posE)
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
		setChanged();
		notifyObservers(new Object[] {});
	}
	public static Espacio getEspacio()
	{
		if(miEspacio == null)
		{
			miEspacio = new Espacio();
		}
		return miEspacio;
	}
	
	public Casilla getCasilla(int x, int y) {
		if(x >= 0 && x < 60 && y >= 0 && y < 100) {
	        return tablero[x][y];
	    }
	    return null;
	}
	public void moverEnem(ArrayList<int[]> posE)
	{
		for(int i=0;i<posE.size();i++)
		{
			int f=posE.get(i)[0];
			int c=posE.get(i)[1];
			Enemigo enem = (Enemigo) tablero[f][c];
			if(tablero[f+1][c] instanceof Disparo)
			{
				//TODO eliminar a este de la colección de enemigos
			}
			else
			{
				tablero[f+1][c] = enem;
			}
			tablero[f][c] = new Casilla();
		}
	}
	public boolean moverNave(String dir, ArrayList<int[]> posN)
	{
		boolean muerto = false;
		for(int i=0;i<posN.size();i++)
		{
			int f=posN.get(i)[0];
			int c=posN.get(i)[1];
			Nave nave= (Nave) tablero[f][c];
			if(dir == "up")
			{
				if(tablero[f-1][c] instanceof Enemigo)
				{
					muerto=true;
				}
				else
				{
					tablero[f-1][c] = nave;
				}
				tablero[f][c] = new Casilla();
				
			}
			else if(dir == "down")
			{
				if(tablero[f+1][c] instanceof Enemigo)
				{
					muerto=true;
				}
				else
				{
					tablero[f+1][c] = nave;
				}
				tablero[f][c] = new Casilla();
				
			}
			else if(dir == "right")
			{
				if(tablero[f][c+1] instanceof Enemigo)
				{
					muerto=true;
				}
				else
				{
					tablero[f][c+1] = nave;
				}
				tablero[f][c] = new Casilla();
				
			}
			else if(dir == "left")
			{
				if(tablero[f][c-1] instanceof Enemigo)
				{
					muerto=true;
				}
				else
				{
					tablero[f][c-1] = nave;
				}
				tablero[f][c] = new Casilla();
				
			}
		}
		return muerto;
	}
	
}
