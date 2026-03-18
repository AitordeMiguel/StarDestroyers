package model;

import java.util.Random;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

public class Espacio extends Observable{
	private Casilla[][] tablero;
	private static Espacio miEspacio;
	private boolean juegoIniciado,finJuego = false;
	private Espacio()
	{
		
	}
	public void inicializar(String color,ArrayList<int[]> posE)
	{
		if(!juegoIniciado)
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
	
	public Casilla getCasilla(int x, int y) {
		if(x >= 0 && x < 60 && y >= 0 && y < 100) {
	        return tablero[x][y];
	    }
	    return null;
	}
	/*public ArrayList<Boolean> moverEnem(ArrayList<int[]> posE)
	{
		ArrayList<Boolean> sol = new ArrayList<Boolean>();
		for(int i=0;i<posE.size();i++)
		{
			boolean movido = false;
			int accion=3;//nada
			
			int[] posNue = new int[2];
			
			int f=posE.get(i)[0];
			int c=posE.get(i)[1];
			
			int[] posA = {f,c};
			
			Enemigo enem = (Enemigo) tablero[f][c];
			if(tablero[f+1][c] instanceof Disparo)
			{
				accion=4;//borrar el disparo enemigo
				// eliminar a este de la colección de enemigos
			}
			//
			// si llega hasta abajo
			else//se puede mover
			{
				accion=0;//mover
				tablero[f+1][c] = enem;
				posNue[0]=f+1;
				posNue[1]=c;
				movido = true;
			}
			tablero[f][c] = new Casilla();
			
			sol.add(movido);
			setChanged();
			notifyObservers(new Object[] {accion,posA,posNue,2});
		}
		return sol;
	}*/
	public ArrayList<int []> moverEnem(ArrayList<int[]> posE)
	{
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
			if(tablero[f][c] instanceof Enemigo)
			{
				Enemigo enem = (Enemigo) tablero[f][c];//TODO marcar fin con boolean?
				tablero[f][c]= new Casilla();//se borra el enemigo o el enemigo deja de esatr en esta casilla
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
				else if(tablero[f+1][c] instanceof Disparo)
				{
					accion=4;//borrar el disparo enemigo
					posNue[0]=f+1;
					posNue[1]=c;
					tablero[f+1][c] = new Casilla(); // Destruimos el disparo quitándolo de la matriz
					valores[0]= 0;//no se ha movido el enem
					valores[1]= 1;//se borra disp
					valores[2]= f+1;
					valores[3]= c;
				}
				// si choca con la Nave del jugador
				else if(tablero[f+1][c] instanceof Nave)
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
					tablero[f+1][c] = enem;
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
	public boolean moverNave(String dir, ArrayList<int[]> posN)
	{
		int estado=2;//nada
		int accion=3;//nada
		boolean movido = false;
		
		int[] posA = new int[2];
		int[] posNue = new int[2];
		
		for(int i=0;i<posN.size();i++)//aunque solo hay una nave
		{
			int f=posN.get(i)[0];
			int c=posN.get(i)[1];
			posA[0]=f;
			posA[1]=c;
			Nave nave = (Nave) tablero[f][c];
			
			if(dir.equals("up"))
			{
				if(f>0)//puede ir hacia arriba
				{
					tablero[f][c] = new Casilla();
					if(tablero[f-1][c] instanceof Enemigo)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else//via libre para moverse
			        {
			            tablero[f-1][c] = nave;
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
					tablero[f][c] = new Casilla();
					if(tablero[f+1][c] instanceof Enemigo)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else
			        {
			            tablero[f+1][c] = nave;
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
					tablero[f][c] = new Casilla();
					if(tablero[f][c+1] instanceof Enemigo)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else
			        {
			            tablero[f][c+1] = nave;
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
					tablero[f][c] = new Casilla();
					if(tablero[f][c-1] instanceof Enemigo)
			        {
			            movido = false;
			            estado=0;//perder
			        }
			        else
			        {
			            tablero[f][c-1] = nave;
			            accion=0;//mover
			            c--;
						movido=true;
			        }
				}
			}
			posNue[0]=f;
			posNue[1]=c;
	    }
		String color ="";
		int[][] tabNum = new int[60][100];
	    setChanged();
	    notifyObservers(new Object[] {accion,posA,posNue,0,estado,juegoIniciado,color,tabNum,finJuego});

		return movido;
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
			
			if(tablero[f][c] instanceof Disparo)
			{
				Disparo disp = (Disparo) tablero[f][c];
				
				if(disp.getTipo()=="normal")
				{
					if(f==0)//no puede haber enemigos más allá
					{
						valores[0] = 0;//no se ha movido
						valores[1] = 0;//no se borra enem
						tablero[f][c] = new Casilla();
						accion=1; //borrar
						
					}
					else if(tablero[f-1][c] instanceof Enemigo)//directamente se borra el enemigo
					{
						valores[0] = 0;//no se ha movido
						valores[1] = 1;//se borra enem
						valores[2] = f-1;
						valores[3] = c;
						
						accion=4;//borrar2 (borra enemigo y disparo) 
						tipo=2;//lo que se borrará será un enem
						tablero[f][c] = new Casilla();
						tablero[f-1][c] = new Casilla();
						//posA[0]--;
						posNue[0]=f-1;
						posNue[1]=c;
					}
					else if(f>0)
					{
						valores[0] = 1;//se ha movido
						valores[1] = 0;//no se borra enem
						
						tablero[f][c] = new Casilla();
						tablero[f-1][c] = disp;
						accion = 0;//mover
						posNue[0]=f-1;
						posNue[1]=c;
					}
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
		
		if(tipo=="normal" && f>=2)
		{
			sol[1] = f-2;
			sol[2] = c;
			if(tablero[f-2][c] instanceof Enemigo)
			{
				//notificar que se borra Enemigo, pero no se crea
				sol[0] = 0;// No creado
				sol[3] = 1;
				accion=1;//se va a borrar
				posA[0] = f-2;
				posA[1] = c;
				tip=2;//se borrara un enem
			}
			else if(tablero[f-2][c] instanceof Disparo )//aunque como se mueve no debería poder ocurrir
			{
				sol[0] = 0;// No creado
				sol[3] = 0;//No se elimina enem
			}
			else if(f-3>=0 && tablero[f-3][c] instanceof Disparo)//me aseguro de que haya espacios entre disp
			{
				sol[0] = 0;// No creado
				sol[3] = 0;//No se elimina enem
			}
			else
			{
				sol[0] = 1;//creado
				sol[3] = 0;
				tablero[f-2][c] = new Disparo(tipo);
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
