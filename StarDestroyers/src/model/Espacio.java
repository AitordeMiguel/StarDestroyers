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
	public void inicializar(String color,ArrayList<int[]> posE)
	{
		if(!juegoIniciado)
		{
			tablero = new int[60][100];
			for(int f=0;f<60;f++)
			{
				for(int c=0;c<100;c++)
				{
					if(!posE.isEmpty() &&f==2 && c==posE.get(0)[1])
					{
						posE.remove(0);
						tablero[f][c] = 2;
					}
					else if((f==55 && c==50)||(f==55 && c==49)||(f==54 && c==50)||(f==56 && c==50))//Todas las pos de nuestra nave
					{
						tablero[f][c] = 0;
					}
					else
					{
						tablero[f][c] = 3;
					}
				}
			}
			int[][] tabNum = tablero;
			int accion=3;//nada
			int[] posA = {};
			int[] posNue = {};
			int estado = 2;//seguir jugando
			setChanged();
			notifyObservers(new Object[] {accion,posA,posNue,2,estado,juegoIniciado,color,tabNum,finJuego});
			juegoIniciado = true;
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
		/*
		for(int i=0;i<posN.size();i++)//aunque solo hay una nave
		{
			int f=posN.get(i)[0];
			int c=posN.get(i)[1];
			posA[0]=f;
			posA[1]=c;
			//Nave nave = (Nave) tablero[f][c];
			
			if(dir.equals("up"))
			{
				if(f>0)//puede ir hacia arriba
				{
					tablero[f][c] = 3;
					if(tablero[f-1][c] == 2)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else//via libre para moverse
			        {
			            tablero[f-1][c] = 0; //nave
			            accion=0;//mover
			            movido=true;
			            f--;
			        }
				}
			}
			else if(dir.equals("down"))
			{
				if(f<59)
				{
					tablero[f][c] = 3;
					if(tablero[f+1][c] == 2)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else
			        {
			            tablero[f+1][c] = 0;
			            accion=0;//mover
			            f++;
						movido=true;
			        }
				}
			}
			else if(dir.equals("right"))
			{
				if(c<99)
				{
					tablero[f][c] = 3;
					if(tablero[f][c+1] == 2)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else
			        {
			            tablero[f][c+1] = 0;
			            accion=0;//mover
			            c++;
						movido=true;
			        }
				}
			}
			else if(dir.equals("left"))
			{
				if(c>0)
				{
					tablero[f][c] = 3;
					if(tablero[f][c-1] == 2)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else
			        {
			            tablero[f][c-1] = 0;
			            accion=0;//mover
			            c--;
						movido=true;
			        }
				}
			}
			posNue[0]=f;
			posNue[1]=c;
	    }
		*/
		String color ="";
		int[][] tabNum = new int[60][100];
	    setChanged();
	    notifyObservers(new Object[] {accion,posA,posNue,0,estado,juegoIniciado,color,tabNum,finJuego});//TODO cambiarlo a pasar toda la matriz

		return movido;
	}
	
	public void desdibujarNave(int[] posN)
	{
		tablero[posN[0]][posN[1]] = 3;//Ahora es vacío
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
	public int[] crearDisp(int[] posN, String tipo)
	{
		//boolean a int  --> 1=True, 0=False
		int[] sol = new int[4];//boolean creado, pos x, pos y, borrarEnem?
		int f = posN[0];
		int c = posN[1];
		int accion=3;//nada
		int tip =1;//disparo, aunque puede cambiar
		int estado = 2;
		sol[0] = 0;//de momento no creado
		
		int[] posA = {-1,-1};//porque no lo vamos a querer, al menos de momento
		
		if(tipo=="normal" && f>=2)//TODO cambiarlo para que sencillamente lo cree, y hacer otro método que sea comprobar si se puede crear
		{
			sol[1] = f-2;
			sol[2] = c;
			if(tablero[f-2][c] == 2)
			{
				//notificar que se borra Enemigo, pero no se crea
				sol[0] = 0;// No creado
				sol[3] = 1;
				accion=1;//se va a borrar
				posA[0] = f-2;
				posA[1] = c;
				tip=2;//se borrara un enem
			}
			else if(tablero[f-2][c] == 1)//aunque como se mueve no debería poder ocurrir
			{
				sol[0] = 0;// No creado
				sol[3] = 0;//No se elimina enem
			}
			else if(f-3>=0 && tablero[f-3][c] == 1)//me aseguro de que haya espacios entre disp
			{
				sol[0] = 0;// No creado
				sol[3] = 0;//No se elimina enem
			}
			else
			{
				sol[0] = 1;//creado
				sol[3] = 0;
				tablero[f-2][c] = 1;
				accion=2;//crear
			}
		}
		
		int[] posNue = {f-2,c};
		String color ="";
		int[][] tabNum = new int[60][100];
		setChanged();
		notifyObservers(new Object[] {accion,posA,posNue,tip,estado,juegoIniciado,color,tabNum,finJuego});//crear,nada,posAnt,posNue,
		
		return sol;
	}
	public boolean comprobarMoverNave(int f, int c, String dir)
	{
		boolean rdo = true;
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(dir.equals("up"))
			{
				if(f>0 || tablero[f-1][c] == 2)//Si arriba no hay pared o si la de arriba es enemigo
				{
					rdo = false;
				}
			}
			else if(dir.equals("dowm"))
			{
				if(f<59 || tablero[f+1][c] == 2)//Si abajo no hay pared o si la de abajo es enemigo
				{
					rdo = false;
				}
			}
			else if(dir.equals("left"))
			{
				if(c>0 || tablero[f][c-1] == 2)//Si a la izq no hay pared o si la de abajo es enemigo
				{
					rdo = false;
				}
			}
			else if(dir.equals("right"))
			{
				if(c<99 || tablero[f][c+1] == 2)//Si a la derecha no hay pared o si la de abajo es enemigo
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
		int rdo = 1;
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
			}
			else if(tablero[f+1][c]==1)//Si es disparo
			{
				rdo = 3;
			}
			else if(tablero[f+1][c]==0)//Si es nave
			{
				rdo = 2;
			}
		}
		
		return rdo;
	}
	
	public boolean comprobarMoverDisp(int f, int c)
	{
		boolean rdo= true;//Se puede mover?
		
		if(f>=0 && c>=0 && f<60 && c<100)//Si son pos válidas
		{
			if(tablero[f-1][c]==1)//Si es enem el de arriba
			{
				rdo = false;
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
}
