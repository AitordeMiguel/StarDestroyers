package model;
import java.util.ArrayList;

public class ListaDisp{
	private ArrayList<int[]> LDis;
	private static ListaDisp miListaDisp;
	private ListaDisp(String color)
	{
		LDis = new ArrayList<int[]>();
		ListaNaves ln = ListaNaves.getListaNaves(color, LDis);
	}
	public static ListaDisp getListaDisp(String color)
	{
		if(miListaDisp == null)
		{
			miListaDisp = new ListaDisp(color);
		}
		return miListaDisp;
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
	public void actualizarMov()
	{
		ListaNaves.getListaNaves("ya está creado así que no importa", LDis).actualizarMov();;
		
	}
}
