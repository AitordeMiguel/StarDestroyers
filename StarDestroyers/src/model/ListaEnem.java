package model;

import java.util.ArrayList;
import java.util.Random;

public class ListaEnem{
	private ArrayList<int[]> LEnem;
	private static ListaEnem miListaEnem;
	private ListaEnem(){}
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
		Espacio.getEspacio().inicializar(color,LEnem);
	}
	public void removeEnem(int x, int y)
	{
		for(int i=0;i<LEnem.size();i++)
		{
			if(LEnem.get(i)[0]==x && LEnem.get(i)[1]==y)//basta con y
			{
				LEnem.remove(i);
			}
		}
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
	public void moverEnem()
	{
		Espacio.getEspacio().moverEnem(LEnem);
	}
	public boolean moverNave(String dir, ArrayList<int[]> LNav)
	{
		return Espacio.getEspacio().moverNave(dir, LNav);
	}
	public void moverDisp(ArrayList<int[]> LDisp)
	{
		Espacio.getEspacio().moverDisp(LDisp);
	}
	public boolean crearDisp(ArrayList<int[]> LNav,String tipo)
	{
		return Espacio.getEspacio().crearDisp(LNav.get(0), tipo);
	}
}
