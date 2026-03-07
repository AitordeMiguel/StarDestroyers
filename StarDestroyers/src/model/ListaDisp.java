package model;
import java.util.ArrayList;

public class ListaDisp{
	private ArrayList<int[]> LDis;
	private static ListaDisp miListaDisp;
	private ListaDisp(){}
	
	public static ListaDisp getListaDisp()
	{
		if(miListaDisp == null)
		{
			miListaDisp = new ListaDisp();
		}
		return miListaDisp;
	}
	public void inicializar(String color) 
	{
		LDis = new ArrayList<int[]>();
		ListaNaves.getListaNaves().inicializar(color);;
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
		ListaNaves.getListaNaves().actualizarMov();
	}
	public void moverEnem()
	{
		ListaNaves.getListaNaves().moverEnem();	
	}
	public boolean moverNave(String dir)
	{
		return ListaNaves.getListaNaves().moverNave(dir);
	}
}
