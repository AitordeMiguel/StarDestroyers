package model;

import java.util.Random;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

public class Espacio extends Observable{
	private int[][] tablero;  //0=Nave, 1=Disp, 2=Enem, 3=Vacio
	private static Espacio miEspacio;
	private boolean juegoIniciado,finJuego = false;
	private Espacio()
	{
		
	}
	public void inicializar(String color)
	{
		if(!juegoIniciado)
		{
			tablero = new int[60][100];
			for(int f=0;f<60;f++)
			{
				for(int c=0;c<100;c++)
				{
					tablero[f][c] = 3;
				}
			}
		}
		
	}
	public static Espacio getEspacio()
	{
		if(miEspacio == null)
		{
			miEspacio = new Espacio();
		}
		return miEspacio;
	}
	
	public void desdibujar(int[] pos)//Sirve para los tres
	{
		tablero[pos[0]][pos[1]] = 3;//Ahora es vacío
		this.notificar(1/*Al juego*/,2/*Seguir jugando*/, null/*Color que no se usa*/, pos, 0/*Borrar*/, -1/*No se va a usar*/);
	}

	public void dibujarNave(int[] pos)//TODO diferenciar entre inicialización y no
	{
		tablero[pos[0]][pos[1]] = 0;//Es nave
		this.notificar(1/*Al juego*/,2/*Seguir jugando*/, null/*Color que no se usa*/, pos, 1/*Dibujar*/, 0/*Nave*/);
	}
	
	public void dibujarDisp(int[] pos)
	{
		tablero[pos[0]][pos[1]] = 1;//Es disparo
		this.notificar(1/*Al juego*/,2/*Seguir jugando*/, null/*Color que no se usa*/, pos, 1/*Dibujar*/, 1/*Disparo*/);
	}
	
	public void dibujarEnem(int[] pos)
	{
		tablero[pos[0]][pos[1]] = 2;//Es enem
		this.notificar(1/*Al juego*/,2/*Seguir jugando*/, null/*Color que no se usa*/, pos, 1/*Dibujar*/, 2/*Enem*/);
	}
	
	public void crearNave(int[] coor)//Esto es al inicializar  //TODO puede que esto sobre
	{
		tablero[coor[0]][coor[1]] = 0;//Es nave
	}
	
	public void crearEnem(int[] coor)//Esto es al inicializar  //TODO puede que esto sobre
	{
		tablero[coor[0]][coor[1]] = 2;//Es enem
	}
	/*
 	public void crearDisp(int[] coor)//Este no vale, pues no se usa al inicializar 
	{
		tablero[coor[0]][coor[1]] = 1;//Es disparo
	}
	public void redibujarNave(ArrayList<int[]> pos)//Esto es al mover      //TODO puede que esto sobre
	{
		for(int[] coor: pos)
		{
			tablero[coor[0]][coor[1]] = 0;//Es nave
		}
		setChanged();
		notifyObservers(new Object[] {1,tablero,2,juegoIniciado,finJuego});//Notifica a Juego para repintar la matriz
	}
	
	public void redibujarEnem(ArrayList<int[]> pos)//Esto es al mover        //TODO puede que esto sobre
	{
		for(int[] coor: pos)
		{
			tablero[coor[0]][coor[1]] = 2;//Es enem
		}
		setChanged();
		notifyObservers(new Object[] {1,tablero,2,juegoIniciado,finJuego});//Notifica a Juego para repintar la matriz
	}
	*/
	
	public boolean comprobarMoverNave(int f, int c, String dir)
	{
		boolean rdo = true;//Si se puede mover
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(dir.equals("up"))
			{
				if(f==0)//Si arriba hay pared
				{
					rdo = false;
				}
				else if(tablero[f-1][c] == 2)//Si la de arriba es enemigo
				{
					rdo = false;
					notifyFin(0);//notificar que se ha perdido
				}
			}
			else if(dir.equals("down"))
			{
				if(f==59)//Si abajo hay pared
				{
					rdo = false;
				}
				else if(tablero[f+1][c] == 2)//Si la de abajo es enemigo
				{
					rdo = false;
					notifyFin(0);//notificar que se ha perdido
				}
			}
			else if(dir.equals("left"))
			{
				if(c==0)//Si a la izq hay pared
				{
					rdo = false;
				}
				else if(tablero[f][c-1] == 2)//Si la de abajo es enemigo
				{
					rdo = false;
					notifyFin(0);//notificar que se ha perdido
				}
			}
			else if(dir.equals("right"))
			{
				if(c==99)//Si a la derecha hay pared
				{
					rdo = false;
				}
				else if(tablero[f][c+1] == 2)//Si la de abajo es enemigo
				{
					rdo = false;
					notifyFin(0);//notificar que se ha perdido
				}
			}
		}
		else//aunque nunca debería darse este
		{
			rdo = false;
		}
		return rdo;
	}
	
	public boolean comprobarMoverEnem(int f, int c)
	{
		boolean rdo = true;
		
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(f==59)//Si ha llegado al final
			{
				rdo = false;
				notifyFin(0);
			}
			else if(tablero[f+1][c]==1)//Si es disparo       //TODO ¡¡¡¡Cuidado con si choca con +1!!!! al notificar a las listas
			{
				rdo = false;
				this.notificar(2/*A LN*/,2/*Seguir jugando*/, null/*Color que no se usa*/, new int[] {f+1,c}, -1/*No se usa*/, -1/*No se usa*/);
				this.notificar(3/*A LE*/,2/*Seguir jugando*/, null/*Color que no se usa*/, new int[] {f,c}, -1/*No se usa*/, -1/*No se usa*/);
				/*
				setChanged();
				notifyObservers(new Object[] {2,tablero,2,juegoIniciado,finJuego,null,new int[] {f+1,c}});//Notificar a LN
				notifyObservers(new Object[] {3,tablero,2,juegoIniciado,finJuego,null,new int[] {f,c}});//Notificar a LE
				*/
			}
			else if(tablero[f+1][c]==0)//Si es nave
			{
				rdo = false;
				notifyFin(0);
			}
		}
		else//aunque nunca debería darse este
		{
			rdo = false;
		}
		
		return rdo;
	}
	
	public boolean comprobarMoverDisp(int f, int c)
	{
		boolean rdo= false;//Se puede mover?
		
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(f==0)//Se puede mover, luego al moverlo sencillamente se borra
			{
				rdo = true;
			}
			else if(tablero[f-1][c]!=1)//Si el de arriba no es enem
			{
				rdo = true;
			}
			else//TODO ¡¡¡¡Cuidado con si choca con +1!!!!
			{
				this.notificar(2/*A LN*/,2/*Seguir jugando*/, null/*Color que no se usa*/, new int[] {f,c}, -1/*No se usa*/, -1/*No se usa*/);
				this.notificar(3/*A LE*/,2/*Seguir jugando*/, null/*Color que no se usa*/, new int[] {f-1,c}, -1/*No se usa*/, -1/*No se usa*/);
				/*
				setChanged();
				notifyObservers(new Object[] {2,tablero,2,juegoIniciado,finJuego,null,new int[] {f,c}});//Notificar a LN
				notifyObservers(new Object[] {3,tablero,2,juegoIniciado,finJuego,null,new int[] {f-1,c}});//Notificar a LE
				*/
			}
		}
		
		return rdo;
	}
	
	public boolean comprobarCrearDisp(int f, int c)
	{
		boolean rdo= false;//Se puede mover?
		
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(tablero[f][c]==3)//Si la casilla es espacio
			{
				if(f==0)//Si se está creando el la fila de arriba
				{
					rdo= true;
				}
				else//Si hay más filas arriba
				{
					if(tablero[f-1][c]==3 || tablero[f-1][c]==2)//Si la casilla de arriba está vacía o enem
					{
						rdo = true;
					}
					else//Aunque este else es innecesario
					{
						rdo=false;
					}
				}
			}
			else if(tablero[f][c] == 2)//Si la casilla es directamente enem
			{
				rdo = false;
				this.notificar(3/*A LE*/,2/*Seguir jugando*/, null/*Color que no se usa*/, new int[] {f,c}, -1/*No se usa*/, -1/*No se usa*/);
				/*
				setChanged();
				notifyObservers(new Object[] {3,tablero,2,juegoIniciado,finJuego,null,new int[] {f,c}});//Avisar a la LE de borrar su enem
				*/
			}
		}
		
		return rdo;
	}
	
	public void notifyFin(int estado)//0=perder, 1=ganar 
	{
		setChanged();
		notifyObservers(new Object[] {1,tablero,estado,juegoIniciado,finJuego});//No envia más cosas, pues no se van a usar
		finJuego = true;
	}
	public void notificar(int dest, int estado, String color, int[] pos,/* ArrayList<int[]> coor,*/int accion,int tipo)
	{
		setChanged();
		notifyObservers(new Object[] {dest,tablero,estado,juegoIniciado,finJuego,color,pos,/*coor,*/accion,tipo});//
		juegoIniciado  = true;//Es porque en cuanto se haga una notificación se habrá empezado el juego, y luego ya no cambia
	}
	
}
