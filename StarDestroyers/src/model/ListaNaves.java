package model;

import java.util.ArrayList;

public class ListaNaves extends Lista{
	private ArrayList<int[]> LNav;
	private static ListaNaves miListaNaves;
	private ListaNaves(String color,ArrayList<int[]> LDis)
	{
		LNav = new ArrayList<int[]>();
		addNave(55,50);
		ListaEnem le = ListaEnem.getListaEnem(color,LDis,LNav);
	}
	public static ListaNaves getListaNaves(String color,ArrayList<int[]> LDis)
	{
		if(miListaNaves == null)
		{
			miListaNaves = new ListaNaves(color,LDis);
		}
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
}
