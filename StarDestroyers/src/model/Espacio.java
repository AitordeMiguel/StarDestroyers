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
			setChanged();
			notifyObservers(new Object[] {0,tablero,null,juegoIniciado,finJuego,color});//TODO cambiar el primero para que no vaya a Menú, sino a una lista para seguir
			juegoIniciado = true;//TODO quitar esto y ponerlo en el método final de la inicialización
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
	
	/*
	private int getCasilla(int x, int y) {
		if(x >= 0 && x < 60 && y >= 0 && y < 100) {
	        return tablero[x][y];
	    }
	    return -1;
	}
	*/
	
	public ArrayList<int []> moverEnem(ArrayList<int[]> posE)//TODO aquí debe recoger solo un disparo, que será un array de coordenadas x e y por el Composite
	{//TODO cambiar toda la estructura para que:
		/*
		 1ro: Borre todas las posiciones antiguas
		 2do: Pinte todas las nuevas posiciones
		 Aclaración: Es para evitar que si muevo una de abajo a arriba y ya había una ahí, luego la borre.
		 */
		ArrayList<int []> sol = new ArrayList<int []>();
		for(int i=0;i<posE.size();i++)
		{
			//boolean movido = false;
			int accion=3;//nada
			
			int[] posNue = new int[2];
			int[] valores = new int[4];//se ha movido el enemigo?, se borra disp?, posX, posY   --pos solo para disp
			//valores[0] : 0=no mueve, 1=mueve, 2=perder
			int f=posE.get(i)[0];
			int c=posE.get(i)[1];
			
			int[] posA = {f,c};
			
			int estado = 2;
			if(tablero[f][c] == 2)
			{
				//Enemigo enem = (Enemigo) tablero[f][c];//TODO marcar fin con boolean?
				tablero[f][c]= 3;//se borra el enemigo o el enemigo deja de esatr en esta casilla
				//si llega hasta abajo (fuera de la matriz)
				if(f + 1 >= 60) 
				{
					accion=1; // borrar en la vista
					posNue[0]=f; 
					posNue[1]=c;
					valores[0]= 2;//no se ha movido el enem, pero directamente ha perdido
					valores[1]= 0;//no borra disp
					estado = 0;//perder
				}
				//si choca con un disparo
				else if(tablero[f+1][c] == 1)
				{
					accion=4;//borrar el disparo enemigo
					posNue[0]=f+1;
					posNue[1]=c;
					tablero[f+1][c] = 3; // Destruimos el disparo quitándolo de la matriz
					valores[0]= 0;//no se ha movido el enem
					valores[1]= 1;//se borra disp
					valores[2]= f+1;
					valores[3]= c;
				}
				// si choca con la Nave del jugador
				else if(tablero[f+1][c] == 1)
				{
					accion=4; //borrar 
					posNue[0]=f+1;
					posNue[1]=c;
					valores[0]= 0;//no se ha movido el enem
					valores[1]= 0;//no borra disp
					estado = 0; // jugador pierde
				}
				else
				{
					accion=0;//mover
					tablero[f+1][c] = 2;//Se ha movido a aquí el enemigo
					posNue[0]=f+1;
					posNue[1]=c;
					valores[0]= 1;// se ha movido el enem
					valores[1]= 0;//no borra disp
				}
				
				sol.add(valores);
				String color ="";
				int[][] tabNum = new int[60][100]; 
				setChanged();
				notifyObservers(new Object[] {accion,posA,posNue,2,estado,juegoIniciado,color,tabNum,finJuego});
			}
		}
		return sol;
	}
	public boolean moverNave(int[] posN)//En este método sabemos que podemos hacerlo
	{
		int estado=2;//nada
		int accion=3;//nada
		boolean movido = false;
		
		int[] posA = new int[2];
		int[] posNue = new int[2];
		
		int f = posN[0];
		int c = posN[1];
		
		//Ya se han borrado todas las posiciones antiguas 
		tablero[f][c] = 0;//Esa casilla ahora es nave
		String color ="";
		int[][] tabNum = new int[60][100];
	    setChanged();
	    notifyObservers(new Object[] {accion,posA,posNue,0,estado,juegoIniciado,color,tabNum,finJuego});//TODO cambiarlo a pasar toda la matriz

		return movido;
	}
	
	public void desdibujar(int[] pos)//Sirve para los tres
	{
		tablero[pos[0]][pos[1]] = 3;//Ahora es vacío
	}
	
	public ArrayList<int[]> moverDisp(ArrayList<int[]> LDisp)
	{
		ArrayList<int[]> sol = new ArrayList<int[]>();
		for(int i=0;i<LDisp.size();i++)
		{
			int f = LDisp.get(i)[0];
			int c = LDisp.get(i)[1];
			
			int accion=3;//nada
			int tipo = 1;//disp
			int[] posA = {f,c};
			int[] posNue = new int[2];
			int estado = 2;//seguir
			
			int[] valores = new int[4];//se ha movido el disparo?, se borra enem?, posX, posY   --pos solo para enem
			
			if(tablero[f][c] == 1)//Es disparo
			{
				//Disparo disp = (Disparo) tablero[f][c];
				
				if(f==0)//no puede haber enemigos más allá
				{
					valores[0] = 0;//no se ha movido
					valores[1] = 0;//no se borra enem
					tablero[f][c] = 3;
					accion=1; //borrar
					
				}
				else if(tablero[f-1][c] == 2)//directamente se borra el enemigo
				{
					valores[0] = 0;//no se ha movido
					valores[1] = 1;//se borra enem
					valores[2] = f-1;
					valores[3] = c;
					
					accion=4;//borrar2 (borra enemigo y disparo) 
					tipo=2;//lo que se borrará será un enem
					tablero[f][c] = 3;
					tablero[f-1][c] = 3;
					//posA[0]--;
					posNue[0]=f-1;
					posNue[1]=c;
				}
				else if(f>0)
				{
					valores[0] = 1;//se ha movido
					valores[1] = 0;//no se borra enem
					
					tablero[f][c] = 3;
					tablero[f-1][c] = 1;//El disp
					accion = 0;//mover
					posNue[0]=f-1;
					posNue[1]=c;
				}
			}
			
			sol.add(valores);
			String color ="";
			int[][] tabNum = new int[60][100];
			setChanged();
			notifyObservers(new Object[] {accion,posA,posNue,tipo,estado,juegoIniciado,color,tabNum,finJuego});
		}
		return sol;
	}
	public void crearDisp(int[] pos)
	{
		tablero[pos[0]][pos[1]] = 1;//Es disparo
		int estado = 2;
		setChanged();
		notifyObservers(new Object[] {1,tablero,estado,juegoIniciado,finJuego});//TODO determinar si quitar este notify y tener un método Notify que avise cuando se hagan todos los píxeles
		
	}
	public void crearNave(int[] pos)
	{
		tablero[pos[0]][pos[1]] = 0;//Es nave
	}
	
	public void crearEnem(int[] pos)
	{
		tablero[pos[0]][pos[1]] = 2;//Es enem
	}
	
	public boolean comprobarMoverNave(int f, int c, String dir)
	{
		boolean rdo = true;
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(dir.equals("up"))
			{
				if(f==0 || tablero[f-1][c] == 2)//Si arriba hay pared o si la de arriba es enemigo
				{
					rdo = false;
				}
			}
			else if(dir.equals("down"))
			{
				if(f==59 || tablero[f+1][c] == 2)//Si abajo hay pared o si la de abajo es enemigo
				{
					rdo = false;
				}
			}
			else if(dir.equals("left"))
			{
				if(c==0 || tablero[f][c-1] == 2)//Si a la izq hay pared o si la de abajo es enemigo
				{
					rdo = false;
				}
			}
			else if(dir.equals("right"))
			{
				if(c==99 || tablero[f][c+1] == 2)//Si a la derecha hay pared o si la de abajo es enemigo
				{
					rdo = false;
				}
			}
		}
		else//aunque nunca debería darse este
		{
			rdo = false;
		}
		return rdo;
	}
	
	public int comprobarMoverEnem(int f, int c)
	{
		int rdo = 1;//TODO plantearse el cambiarlo ha 
		/*
		  1: Se puede mover
		  2: Se ha perdido (Choca contra nave)
		  3: Se borra enem (Choca contra disp)
		 */
		
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(f==59)
			{
				rdo = 2;
				notifyFin(0);
			}
			else if(tablero[f+1][c]==1)//Si es disparo
			{
				rdo = 3;
			}
			else if(tablero[f+1][c]==0)//Si es nave
			{
				rdo = 2;
				notifyFin(0);
			}
		}
		
		return rdo;
	}
	
	public boolean comprobarMoverDisp(int f, int c)
	{
		boolean rdo= false;//Se puede mover?
		
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(tablero[f-1][c]!=1)//Si el de arriba no es enem
			{
				rdo = true;
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
					if(tablero[f-1][c]==3)//Si la casilla de arriba está vacía
					{
						rdo = true;
					}
					else//Aunque este else es innecesario
					{
						rdo=false;
					}
				}
			}
		}
		
		return rdo;
	}
	
	public void anunciarVictoria()
	{
		int[] posA = {-1,-1};
		int[] posN = {-1,-1};
		String color ="";
		int[][] tabNum = new int[60][100];
		setChanged();
		notifyObservers(new Object[] {4,posA,posN,4,1,juegoIniciado,color,tabNum,finJuego});//nada,nada,posAnt,posNue,da igual, ganar
		finJuego = true;
	}
	public void notifyFin(int estado)//0=perder, 1=ganar 
	{
		setChanged();
		notifyObservers(new Object[] {1,tablero,estado,juegoIniciado,finJuego});//
		finJuego = true;
	}
	
	public void notificar()//0=perder, 1=ganar
	{
		int estado = 2;//seguir
		setChanged();
		notifyObservers(new Object[] {1,tablero,estado,true,false,"red"});//
		finJuego = true;
	}
}
