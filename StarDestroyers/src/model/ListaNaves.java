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
		for(Nave n: LNaves)//Solo tenemos una nave, seguramente si tuviesemos más, no sería así, si no individualmente
		{
			n.mover(dir);
		}
		
	}
	public void moverDisp()
	{
		LNaves.get(0).mover("up"); 
	}
	public void crearDisp(String tipo)
	{
		//De momento solo hay una nave, por lo que basta con hacerlo con esa sin escoger entre varias
		LNaves.get(0).disparar();
	}
	public void cambiarDisp() {
		if (LNaves != null && !LNaves.isEmpty()) {
			LNaves.get(0).rotarStrategy(); 
		}
	}
	public void removeDisp(int[] coor)
	{
		LNaves.get(0).borrarDisp(coor);
	}
	@Override
	public void update(Observable o, Object arg) 
	{
		Object[] res = (Object[]) arg;//arg: destinatario,tablero,estado,juegoInic,finJuego,color,accion,coordenadas
		int destinatario = (int) res[0];
		if(destinatario == 2)//Si va dirigido a LN
		{
			this.removeDisp((int[]) res[6]);
		}
	}
	
}
