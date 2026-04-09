package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ListaNaves implements Observer{
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
		Espacio.getEspacio().addObserver(this);	
		LNaves = new ArrayList<Nave>();
		int[] pos = {55,50};
		LNaves.add(fabricarNave(color,pos));
		for(Nave n: LNaves)//Aunque solo hay una
		{
			n.dibujar(); //Dibujarlo en el tablero
		}
		
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
	@Override
	public void update(Observable o, Object arg) 
	{
		Object[] res = (Object[]) arg;//arg: destinatario,tablero,estado,juegoInic,finJuego,color,accion,coordenadas
		int destinatario = (int) res[0];
		if(destinatario == 2 || destinatario == 4)//Si va dirigido a LN
		{
			if((int) res[6] == 0)//Si la acción es inicializar
			{
				inicializar((String) res[5]);
			}
			else//Si la acción es borrar
			{
				int[] coor = (int[]) res[7];
				int x = coor[0];
				int y = coor[1];
				// TODO Remove Disp
			}
		}
		
	}
}
