package model;

import java.util.ArrayList;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Observable;
import java.util.Observer;

public class ListaEnem implements Observer{
	private ArrayList<Enemigo> LEnems;
	private static ListaEnem miListaEnem;
	private Timer timer = null;
	private ListaEnem()
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				//moverEnem();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 200);
		Espacio.getEspacio().addObserver(this);	
	}
	
	
	//public void addEnem(int x, int y)
	//{
	//	int[] coor = {x,y};
	//	LEnem.add(coor);
	//}
	
	public static ListaEnem getListaEnem()
	{
		if(miListaEnem == null)
		{
			miListaEnem = new ListaEnem();
		}
		return miListaEnem;
	}
	private Enemigo fabricarEnemigos(int[] posiciones)
	{
		// llamamos al factory indicando tipo 1 para Enemigo, null en color y las posiciones
		return (Enemigo) Factory.getFactory().generar(1, null, posiciones);
	}
	public void inicializar()
	{
		LEnems = new ArrayList<Enemigo>();
		int cantEnem = new Random().nextInt(4,9);
		int dist=90/cantEnem;
		for(int i=0;i<cantEnem;i++)
		{
			int[] pos = {2,5+i*dist};;
			LEnems.add(fabricarEnemigos(pos));
		};
		for(Enemigo e: LEnems)
		{
			e.dibujar();
		}
	}
	private void removeEnem(int x, int y)
	{
		int i=0;
		boolean enc = false;
		while(i<LEnems.size() && !enc)
		{
			Enemigo enem = LEnems.get(i);
			enc = enem.encontrar(x, y);
			if(enc)
			{
				enem.borrar();
			}
			LEnems.remove(i);
			i++;
		}
		compTamEnem();
	}
	private void compTamEnem()
	{
		if(LEnems.size()==0)
		{
			Espacio.getEspacio().anunciarVictoria();
		}
	}
	private void actEnem(int x, int y)
	{
		/*
		for(int i=0;i<LEnem.size();i++)
		{
			if(LEnem.get(i)[0]==x && LEnem.get(i)[1]==y)
			{
				LEnem.get(i)[0]=x+1; //Baja una posición
			}
		}
		*/
	}
	public void moverEnem() //version postLabo
	{
		//if (LEnem == null || Espacio.getEspacio() == null) { //el timer empieza a contar antes de que se cree la lista de Enemigos, por lo que daba error, le he añadido esto para que el contador empiece a dar vueltas solo cuando está creado la lista.
			//return; 
		//}

		/*
		ArrayList<int []> rdo = Espacio.getEspacio().moverEnem(LEnem);
		for(int i=0;i<rdo.size();i++)
		{
			if(rdo.get(i)[0]==1)//si se ha movido
			{
				LEnem.get(i)[0]++; //Baja una posición
			}
			else if(rdo.get(i)[0]==2)//ha perdido
			{
				//Se ha perdido no anuncio victoria//compTamEnem();
			}
			else//ha chocado
			{
				LEnem.remove(i); //Se elimina este
				rdo.remove(i);
				i--;
			}
		}
		return rdo;
		*/
	}
	public boolean moverNave(String dir, ArrayList<int[]> LNav)
	{
		return Espacio.getEspacio().moverNave(LNav.get(0));
	}
	public ArrayList<int[]> moverDisp(ArrayList<int[]> LDisp)
	{
		ArrayList<int[]> rdo = Espacio.getEspacio().moverDisp(LDisp);
		for(int i=0;i<rdo.size();i++)
		{
			if(rdo.get(i)[1]==1)//se borra enemigo
			{
				removeEnem(rdo.get(i)[2], rdo.get(i)[3]);
			}
		}
		return rdo;
	}
	public void crearDisp(ArrayList<int[]> LNav,String tipo)
	{
		/*
		int[] rdo = Espacio.getEspacio().crearDisp(LNav.get(0), tipo);
		if(rdo[3]==1)//se borra este enem
		{
			removeEnem(rdo[1],rdo[2]);
		}
		return rdo;
		*/
	}


	@Override
	public void update(Observable o, Object arg) 
	{
		
		Object[] res = (Object[]) arg;//arg: destinatario,tablero,estado,juegoInic,finJuego,color,accion,coordenadas
		int destinatario = (int) res[0];
		if(destinatario == 3 || destinatario == 4)//Si va dirigido a LE
		{
			if((int) res[6] == 0)//Si la acción es inicializar
			{
				inicializar();
			}
			else//Si la acción es borrar
			{
				int[] coor = (int[]) res[7];
				int x = coor[0];
				int y = coor[1];
				removeEnem(x, y);
			}
		}
		
	}
}
