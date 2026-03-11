package model;

import java.util.ArrayList;

public class ListaNaves{
	private ArrayList<int[]> LNav;
	private static ListaNaves miListaNaves;
	private ListaNaves(){}
	public static ListaNaves getListaNaves()
	{
		if(miListaNaves == null)
		{
			miListaNaves = new ListaNaves();
		}
		return miListaNaves;
	}
	public void inicializar(String color)
	{
		LNav = new ArrayList<int[]>();
		addNave(55,50);
		ListaEnem.getListaEnem().inicializar(color);
	}
	private void addNave(int x, int y)
	{
		int[] coor = {x,y};
		LNav.add(coor);
	}
	public void removeNave(int x, int y)
	{
		for (int i=0;i<LNav.size();i++)
		{
			if(LNav.get(i)[0]==x && LNav.get(i)[1]==y)
			{
				LNav.remove(i);
			}
		}
	}
	
	
	public boolean moverNave(String dir)
	{
		return ListaEnem.getListaEnem().moverNave(dir,LNav);
		
	}
	public void moverDisp(ArrayList<int[]> LDisp)
	{
		ListaEnem.getListaEnem().moverDisp(LDisp);
	}
	public boolean crearDisp(String tipo)
	{
		return ListaEnem.getListaEnem().crearDisp(LNav,tipo);
	}
}
