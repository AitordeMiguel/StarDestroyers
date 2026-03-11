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
	public void removeDisp(int x, int y)//TODO tener cuidado cuando sean disparos multi-pixel, pues habrá que cambiarlo
	{
		for(int i=0;i<LDis.size();i++)
		{
			if(LDis.get(i)[0]==x && LDis.get(i)[1]==y)
			{
				LDis.remove(i);
			}
		}
	}
	
	
	public void moverDisp()
	{
		ListaNaves.getListaNaves().moverDisp(LDis);	
	}
	public void crearDisp(String tipo)
	{
		boolean creado = ListaNaves.getListaNaves().crearDisp(tipo);
		//if(creado) {addDisp();}
	}
}
