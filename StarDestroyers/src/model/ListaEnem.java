package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ListaEnem{
	private ArrayList<int[]> LEnem;
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
	public void inicializar(String color)
	{
		LEnem = new ArrayList<int[]>();
		int cantEnem = new Random().nextInt(4,9);
		int dist=90/cantEnem;
		for(int i=0;i<cantEnem;i++)
		{
			int[] pos = {2,5+i*dist};
			LEnem.add(pos);
		};
		Espacio.getEspacio().inicializar(color,new ArrayList<>(LEnem));
	}
	public void removeEnem(int x, int y)
	{
		for(int i=0;i<LEnem.size();i++)
		{
			if(LEnem.get(i)[0]==x && LEnem.get(i)[1]==y)//basta con y
			{
				LEnem.remove(i);
				break;
			}
		}
		//TODO comprobamos aquí si aún quedan enemigos? size()==0
	}
	public void comprobarColNave(ArrayList<int[]> LNav)//TODO determinar si realmente se necesita o si ya se hace con notify
	{
		for(int i=0;i<LNav.size();i++)
		{
			int pXN=LNav.get(i)[0];
			int pYN=LNav.get(i)[1];
			for(int j=0;j<LEnem.size();j++)
			{
				int pXE=LEnem.get(j)[0];
				int pYE=LEnem.get(j)[1];
				if(pXE==pXN && pYE==pYN)
				{
					removeEnem(pXE,pYE);
				}
			}
		}
	}
	public void actEnem(int x, int y)
	{
		for(int i=0;i<LEnem.size();i++)
		{
			if(LEnem.get(i)[0]==x && LEnem.get(i)[1]==y)
			{
				LEnem.get(i)[0]=x+1; //Baja una posición
			}
		}
	}
	public ArrayList<int []> moverEnem() //version postLabo
	{
		if (LEnem == null || Espacio.getEspacio() == null) { //el timer empieza a contar antes de que se cree la lista de Enemigos, por lo que daba error, le he añadido esto para que el contador empiece a dar vueltas solo cuando está creado la lista.
			//return; 
		}

		ArrayList<int []> rdo = Espacio.getEspacio().moverEnem(LEnem);
		for(int i=0;i<rdo.size();i++)
		{
			if(rdo.get(i)[0]==1)//si se ha movido
			{
				LEnem.get(i)[0]++; //Baja una posición
			}
			else
			{
				LEnem.remove(i); //Se elimina este
				rdo.remove(i);
				i--;
			}
		}
		return rdo;
	}
	public boolean moverNave(String dir, ArrayList<int[]> LNav)
	{
		return Espacio.getEspacio().moverNave(dir, LNav);
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
	public int[] crearDisp(ArrayList<int[]> LNav,String tipo)
	{
		int[] rdo = Espacio.getEspacio().crearDisp(LNav.get(0), tipo);
		if(rdo[3]==1)//se borra este enem
		{
			removeEnem(rdo[1],rdo[2]);
		}
		return rdo;
	}
}
