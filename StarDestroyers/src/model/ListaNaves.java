package model;

import java.util.ArrayList;

public class ListaNaves{
	private ArrayList<Nave> LNaves;
	private static ListaNaves miListaNaves;
	private ListaNaves(){}
	public static ListaNaves getListaNaves()
	{
		if(miListaNaves == null)
		{
			miListaNaves = new ListaNaves();
		}
		return miListaNaves;
	}
	private Nave fabricarNave(String color, int[] pos)
	{
		// llamamos al factory indicando tipo 0 para Nave, el color y la lista de posiciones
		return (Nave) Factory.getFactory().generar(0, color, pos);
	}
	public void inicializar(String color)
	{	
		LNaves = new ArrayList<Nave>();
		int[] pos = {55,50};
		LNaves.add(fabricarNave(color,pos));
		for(Nave n: LNaves)//Aunque solo hay una
		{
			n.dibujar(); //Dibujarlo en el tablero
		}
		
		//ListaEnem.getListaEnem().inicializar(color); //TODO a menos que queramos llamar de lista a lista, si no, con Notify
	}
	
	
	public void moverNave(String dir)
	{
		/*
		boolean rdo = ListaEnem.getListaEnem().moverNave(dir,LNav);
		if(rdo)//si se ha movido
		{
			if(dir.equals("left"))
			{
				LNav.get(0)[1]--;
			}
			else if(dir.equals("right"))
			{
				LNav.get(0)[1]++;
			}
			else if(dir.equals("down"))
			{
				LNav.get(0)[0]++;
			}
			else if(dir.equals("up"))
			{
				LNav.get(0)[0]--;
			}
			
		}
		*/
		
	}
	public ArrayList<int[]> moverDisp(ArrayList<int[]> LDisp)
	{
		return ListaEnem.getListaEnem().moverDisp(LDisp);
	}
	public void crearDisp(String tipo)
	{
		//De momento solo hay una nave, por lo que basta con hacerlo con esa sin escoger entre varias
		LNaves.get(0).disparar();
	}
	public void moverEnem() { 
		// ListaEnem.getListaEnem().moverEnem();
	}
}
