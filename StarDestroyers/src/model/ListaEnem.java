package model;

import java.util.ArrayList;
import java.util.Random;

public class ListaEnem extends Lista{
	private ArrayList<int[]> LEnem;
	private static ListaEnem miListaEnem;
	private ListaEnem(String color,ArrayList<int[]> LDis,ArrayList<int[]> LNav)
	{
		LEnem = new ArrayList<int[]>();
		int cantEnem = new Random().nextInt(4,9);
		int dist=90/cantEnem;
		for(int i=0;i<cantEnem;i++)
		{
			int[] pos = {2,5+i*dist};
			LEnem.add(pos);
		}
		comprobarColNave(LNav);
		Espacio esp = Espacio.getEspacio(color,LEnem);
	}
	//public void addEnem(int x, int y)
	//{
	//	int[] coor = {x,y};
	//	LEnem.add(coor);
	//}
	public static ListaEnem getListaEnem(String color,ArrayList<int[]> LDis,ArrayList<int[]> LNav)
	{
		if(miListaEnem == null)
		{
			miListaEnem = new ListaEnem(color,LDis,LNav);
		}
		return miListaEnem;
	}
	public void removeEnem(int x, int y)
	{
		int[] coor = {x,y};
		LEnem.remove(coor);
	}
	public void comprobarColNave(ArrayList<int[]> LNav)
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
}
