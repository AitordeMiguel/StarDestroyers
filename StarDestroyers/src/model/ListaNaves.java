package model;

import java.util.ArrayList;

public class ListaNaves{
	private ArrayList<int[]> LNav;
	private static ListaNaves miListaNaves;
	private ListaNaves(String color)
	{
		LNav = new ArrayList<int[]>();
		addNave(55,50);
		ListaEnem.getListaEnem().inicializar(color);
	}
	public static ListaNaves getListaNaves()
	{
		return miListaNaves;
	}
	public void inicializar(String color)
	{
		miListaNaves = new ListaNaves(color);
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
