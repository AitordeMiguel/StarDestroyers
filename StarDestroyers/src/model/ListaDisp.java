package model;
import java.util.ArrayList;

public class ListaDisp extends Lista{
	private ArrayList<int[]> LDis;
	public ListaDisp()
	{
		LDis = new ArrayList<int[]>();
	}
	public void addDisp(int x, int y)
	{
		int[] coor = {x,y};
		LDis.add(coor);
	}
	public void removeDisp(int x, int y)
	{
		int[] coor = {x,y};
		LDis.remove(coor);
	}
}
