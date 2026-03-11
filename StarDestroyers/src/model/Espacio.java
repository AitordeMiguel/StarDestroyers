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
		int[][] tabNum = new int[60][100];//0=nave, 1=disp, 2=enem, 3=vacio
		for(int f=0;f<60;f++)
		{
			for(int c=0;c<100;c++)
			{
				if(!posE.isEmpty() &&f==2 && c==posE.get(0)[1])
				{
					posE.remove(0);
					tablero[f][c] = new Enemigo();
					tabNum[f][c]=2;
				}
				else if(f==55 && c==50)
				{
					tablero[f][c] = new Nave(color);
					tabNum[f][c]=0;
				}
				else
				{
					tablero[f][c] = new Casilla();
					tabNum[f][c]=3;
				}
			}
		}
		setChanged();
		notifyObservers(new Object[] {color,tabNum});
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
			int estado=2;//nada
			int accion=3;//nada
			
			int[] posNue = new int[2];
			
			int f=posE.get(i)[0];
			int c=posE.get(i)[1];
			
			int[] posA = {f,c};
			
			Enemigo enem = (Enemigo) tablero[f][c];
			if(tablero[f+1][c] instanceof Disparo)
			{
				accion=1;//borrar
				//TODO eliminar a este de la colección de enemigos
			}
			//TODO si llega hasta abajo
			//TODO cambiar pos de la Lista
			else
			{
				accion=0;//mover
				tablero[f+1][c] = enem;
				posNue[0]=f+1;
				posNue[1]=c;
			}
			tablero[f][c] = new Casilla();
			
			setChanged();
			notifyObservers(new Object[] {accion,estado,posA,posNue,2});
		}
		
	}
	public boolean moverNave(String dir, ArrayList<int[]> posN)
	{
		int estado=2;//nada
		int accion=3;//nada
		boolean muerto = false;
		
		int[] posA = new int[2];
		int[] posNue = new int[2];
		
		for(int i=0;i<posN.size();i++)
		{
			int f=posN.get(i)[0];
			int c=posN.get(i)[1];
			posA[0]=f;
			posA[1]=c;
			Nave nave = (Nave) tablero[f][c];
			
			int navef = f;
			int navec = c;
			if(dir.equals("up"))
			{
				navef--;
			}
			else if(dir.equals("down"))
			{
				navef++;
			}
			else if(dir.equals("right"))
			{
				navec++;
			}
			else if(dir.equals("left"))
			{
				navec--;
			}
			posNue[0]=navef;
			posNue[1]=navec;
			if(navef < 0 || navef >= 60 || navec < 0 || navec >= 100) {
				return false;
			}
			if(tablero[navef][navec] instanceof Enemigo)
	        {
	            muerto = true;
	            estado=0;
	        }
	        else
	        {
	            tablero[navef][navec] = nave;
	            accion=0;//mover
	        }

	        tablero[f][c] = new Casilla();

	        posN.get(i)[0] = navef;
	        posN.get(i)[1] = navec;
	    }
		
		
		
	    setChanged();
	    notifyObservers(new Object[] {accion,estado,posA,posNue,0});

		return muerto;
	}
	public void moverDisp(ArrayList<int[]> LDisp,String tipo)
	{
		for(int i=0;i<LDisp.size();i++)
		{
			int f = LDisp.get(i)[0];
			int c = LDisp.get(i)[1];
			
			int accion=3;//nada
			int estado = 2;//nada
			int tip = 1;//disp
			int[] posA = {f,c};
			int[] posNue = new int[2];
			
			Disparo disp = (Disparo) tablero[f][c];
			if(disp.getTipo()=="normal")
			{
				if(f==0)
				{
					tablero[f][c] = new Casilla();
					accion=1; //borrar
				}
				else if(f>0)
				{
					tablero[f][c] = new Casilla();
					tablero[f-1][c] = disp;
					accion = 0;//mover
					posNue[0]=f-1;
					posNue[1]=c;
				}//TODO si al moverse mata enemigo
			}
			setChanged();
			notifyObservers(new Object[] {accion,estado,posA,posNue,tip});
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
			tablero[f-2][c] = new Disparo(tipo);
			//TODO añadir a la lista
		}
		//TODO if tablero[f-2][c] es enemigo --> habrá que decir que el tipo era disparo, y que se ha borrado, y directamente no se crea disp
		
		int[] posA = {-1,-1};//porque no lo vamos a querer
		int[] posNue = {f-2,c};
		
		setChanged();
		notifyObservers(new Object[] {2,2,posA,posNue,1});//crear,nada,posAnt,posNue,disp
		
		return creado;
	}
}
