package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ListaNaves extends Observable implements Observer{
	private ArrayList<PiezaAbs> LNaves;//Realmente solo hay nave
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
	private PiezaAbs fabricarNave(String color, int[] pos)
	{
		// llamamos al factory indicando tipo 0 para Nave, el color y la lista de posiciones
		return Factory.getFactory().generar(0, color, pos);
	}
	public void inicializar(String color)
	{	
		Espacio.getEspacio().addObserver(this);	
		LNaves = new ArrayList<PiezaAbs>();
		int[] pos = {55,50};                 //TODO límites del tablero
		LNaves.add(fabricarNave(color,pos));
		//java8
		LNaves.stream().map(p -> (Nave) p).forEach(n -> n.crear());
		//antiguo
		/*
		for(PiezaAbs p: LNaves)//Aunque solo hay una           //TODO java8
		{
			Nave n =(Nave) p;
			n.crear(); //Dibujarlo en el tablero
		}
		*/
	}
	
	
	public void moverNave(String dir)//TODO java8
	{
		//java8
		LNaves.stream().map(p -> (Nave) p).forEach(n -> n.mover(dir));
		//antiguo
		/*
		for(PiezaAbs p: LNaves)//Solo tenemos una nave, seguramente si tuviesemos más, no sería así, si no individualmente
		{
			Nave n =(Nave) p;
			n.mover(dir);
		}
		*/
	}
	public void moverDisp()
	{
		PiezaAbs p = LNaves.get(0);
		Nave n = (Nave) p;
		n.moverDisp(); 
	}
	public void crearDisp()
	{
		//De momento solo hay una nave, por lo que basta con hacerlo con esa sin escoger entre varias
		PiezaAbs p = LNaves.get(0);
		Nave n = (Nave) p;
		int[] rdo = n.disparar();
		if(rdo[0] == 1 && (rdo[1] == 1 || rdo[1] == 2))//Si se ha disparado
		{
			setChanged();
			notifyObservers(new Object[] {4,null,null,null,null,null,null,null,null,rdo[1],rdo[2]});
		}
	}
	public void cambiarDisp(int tipo) {
		if (LNaves != null && !LNaves.isEmpty()) {
			PiezaAbs p = LNaves.get(0);
			Nave n = (Nave) p;
			if(n.cambiarStrategy(tipo))//Si se ha podido cambiar de estrategia
			{
				setChanged();
				notifyObservers(new Object[] {4,null,null,null,null,null,null,null,null,tipo+2,-1/*no se usa*/});
			}
		}
	}
	public void removeDisp(int[] coor)
	{
		PiezaAbs p = LNaves.get(0);
		Nave n = (Nave) p;
		n.borrarDisp(coor);
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
	public void borrar()
	{
		Nave n = (Nave) LNaves.get(0);
		n.borrar();
	}
}
