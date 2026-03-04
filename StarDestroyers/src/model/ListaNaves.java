package model;

import java.util.ArrayList;

public class ListaNaves{
	private ArrayList<int[]> LNav;
	private static ListaNaves miListaNaves;
	private ListaNaves(String color,ArrayList<int[]> LDis)
	{
		LNav = new ArrayList<int[]>();
		addNave(55,50);
		ListaEnem le = ListaEnem.getListaEnem();
	}
	public static ListaNaves getListaNaves()
	{
		return miListaNaves;
	}
	private void addNave(int x, int y)
	{
		int[] coor = {x,y};
		LNav.add(coor);
	}
	public void removeNave(int x, int y)
	{
		int[] coor = {x,y};
		LNav.remove(coor);
	}
	public void actualizarMov()
	{
		
	}
}
