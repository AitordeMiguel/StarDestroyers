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
	public void moverDisp(ArrayList<int[]> LDisp,String tipo)
	{
		for(int i=0;i<LDisp.size();i++)
		{
			int f = LDisp.get(i)[0];
			int c = LDisp.get(i)[1];
			Disparo disp = (Disparo) tablero[f][c];
			if(disp.getTipo()=="punto")
			{
				tablero[f][c] = new Casilla();
				tablero[f][c] = disp;
			}
			else if(disp.getTipo()=="flecha")
			{
				if(f==1)
				{
					tablero[f-1][c] = new Casilla();//borrar antigua punta
					//pintar lados nuevos
					Disparo izq = (Disparo) tablero[f][c-1];
					Disparo der = (Disparo) tablero[f][c+1];
					tablero[f-1][c-1] = izq;
					tablero[f-1][c+1] = der;
					//borrar lados antiguos
					tablero[f][c-1] = new Casilla();
					tablero[f][c+1] = new Casilla();
					
					//TODO actualizar pos disp
				}
				else if(f==0)
				{
					//borrar lados antiguos
					tablero[f][c-1] = new Casilla();
					tablero[f][c+1] = new Casilla();
					//TODO borrar disparo
				}
				else if(f>1)
				{
					Disparo punta = (Disparo) tablero[f-1][c];
					Disparo izq = (Disparo) tablero[f][c-1];
					Disparo der = (Disparo) tablero[f][c+1];
					tablero[f-2][c] = punta;
					tablero[f-1][c] = new Casilla();
					//pintar lados nuevos
					tablero[f-1][c-1] = izq;
					tablero[f-1][c+1] = der;
					//borrar lados antiguos
					tablero[f][c-1] = new Casilla();
					tablero[f][c+1] = new Casilla();
				}
			}
			else if(disp.getTipo()=="rombo")
			{
				if(f==4)
				{
					//primera fila
					tablero[0][c-1] = new Disparo();
					tablero[0][c+1] = new Disparo();
					//segunda fila
					tablero[1][c-2] = new Disparo();
					tablero[1][c+2] = new Disparo();
					//tercera fila
					tablero[2][c-2] = new Casilla();
					tablero[2][c+2] = new Casilla();
					//cuarta fila
					tablero[3][c-1] = new Casilla();
					tablero[3][c+1] = new Casilla();
					//quinta fila
					tablero[4][c] = new Casilla();
					//nuevo f
					//TODO que la nueva pos sea una menos
				}
				else if(f==3)
				{
					//primera fila
					tablero[0][c-2] = new Disparo();
					tablero[0][c+2] = new Disparo();
					//segunda fila
					tablero[1][c-2] = new Casilla();
					tablero[1][c+2] = new Casilla();
					//tercera fila
					tablero[2][c-1] = new Casilla();
					tablero[2][c+1] = new Casilla();
					//cuarta fila
					tablero[3][c] = new Casilla();
					//nuevo f
					//TODO que la nueva pos sea una menos
				}
				else if(f==2)
				{
					//primera fila
					tablero[0][c-2] = new Disparo();
					tablero[0][c+2] = new Disparo();
					//segunda fila
					tablero[1][c-1] = new Casilla();
					tablero[1][c+1] = new Casilla();
					//tercera fila
					tablero[2][c] = new Casilla();
					//nuevo f
					//TODO que la nueva pos sea una menos
				}
				else if(f==1)
				{
					//primera fila
					tablero[0][c-1] = new Casilla();
					tablero[0][c+1] = new Casilla();
					//segunda fila
					tablero[1][c] = new Casilla();
					//nuevo f
					//TODO que la nueva pos sea una menos
				}
				else if(f==0)
				{
					//primera fila, punta
					tablero[0][c] = new Casilla();
					//nuevo f, que será -1
					//TODO que la nueva pos sea una menos
				}
				else if(f>4)
				{
					//pintar
					tablero[f-5][c] = new Disparo();//nueva punta
					tablero[f-4][c-1] = new Disparo();//nueva fila 2 izq
					tablero[f-4][c+1] = new Disparo();//nueva fila 2 der
					tablero[f-3][c-2] = new Disparo();//nueva fila 3 izq
					tablero[f-3][c+2] = new Disparo();//nueva fila 3 der
					//borrar
					tablero[f-2][c-2] = new Casilla();
					tablero[f-2][c+2] = new Casilla();
					tablero[f-1][c-1] = new Casilla();
					tablero[f-1][c+1] = new Casilla();
					tablero[f][c] = new Casilla();
					//nuevo f
					//TODO que la nueva pos sea una menos
				}
			}
		}
	}
	public boolean crearDisp(int[] posN, String tipo)
	{
		int f = posN[0];
		int c = posN[1];
		boolean creado = false;
		if(tipo=="normal" && f>=2)
		{
			creado = true;
			tablero[f+2][c] = new Disparo();
			//TODO añadir a la lista
		}
		return creado;
	}
}
