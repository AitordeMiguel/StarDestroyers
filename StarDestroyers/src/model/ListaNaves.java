package model;

import java.util.ArrayList;

public class ListaNaves extends Lista{
	private ArrayList<int[]> LNav;
	public ListaNaves()
	{
		LNav = new ArrayList<int[]>();
	}
	public void addDisp(int x, int y)
	{
		int[] coor = {x,y};
		LNav.add(coor);
	}
	public void removeDisp(int x, int y)
	{
		int[] coor = {x,y};
		LNav.remove(coor);
	}
}
