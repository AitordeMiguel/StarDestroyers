package model;

import java.util.ArrayList;

public class ListaNaves{
	private ArrayList<int[]> LNav;
	private ArrayList<Nave> LNaves;//TODO que sustituya al de arriba
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
	private Nave fabricarNave(String color, int[] pos)
	{
		// llamamos al factory indicando tipo 0 para Nave, el color y la lista de posiciones
		return (Nave) Factory.getFactory().generar(0, color, pos);
	}
	public void inicializar(String color)
	{
		LNav = new ArrayList<int[]>();
		addNave(55,50);
		
		LNaves = new ArrayList<Nave>();
		int[] pos = {55,50};
		LNaves.add(fabricarNave(color,pos));
		
		ListaEnem.getListaEnem().inicializar(color);
	}
	private void addNave(int x, int y)
	{
		int[] coor = {x,y};
		LNav.add(coor);
	}
	
	private void removeNave(int x, int y)
	{
		for (int i=0;i<LNav.size();i++)
		{
			if(LNav.get(i)[0]==x && LNav.get(i)[1]==y)
			{
				LNav.remove(i);
			}
		}
	}
	
	
	public void moverNave(String dir)
	{
		boolean rdo = ListaEnem.getListaEnem().moverNave(dir,LNav);
		if(rdo)//si se ha movido
		{
			if(dir.equals("left"))
			{
				LNav.get(0)[1]--;
			}
			else if(dir.equals("right"))
			{
				LNav.get(0)[1]++;
			}
			else if(dir.equals("down"))
			{
				LNav.get(0)[0]++;
			}
			else if(dir.equals("up"))
			{
				LNav.get(0)[0]--;
			}
			
		}
		
	}
	public ArrayList<int[]> moverDisp(ArrayList<int[]> LDisp)
	{
		return ListaEnem.getListaEnem().moverDisp(LDisp);
	}
	public int[] crearDisp(String tipo)
	{
		return ListaEnem.getListaEnem().crearDisp(LNav,tipo);
	}
	public ArrayList<int []> moverEnem() { 
		return ListaEnem.getListaEnem().moverEnem();
		
	}
}
