package model;

import java.util.ArrayList;

public class ListaEnem extends Lista{
	private ArrayList<int[]> LEnem;
	public ListaEnem()
	{
		LEnem = new ArrayList<int[]>();
	}
	public void addDisp(int x, int y)
	{
		int[] coor = {x,y};
		LEnem.add(coor);
	}
	public void removeDisp(int x, int y)
	{
		int[] coor = {x,y};
		LEnem.remove(coor);
	}
}
