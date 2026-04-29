package model;

import java.util.ArrayList;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.Observable;
import java.util.Observer;

public class ListaEnem implements Observer{
	private ArrayList<Enemigo> LEnems;
	private static ListaEnem miListaEnem;
	private ScheduledExecutorService scheduler;
	private int cont = 0;
	private boolean inicializado = false;
	private ListaEnem()
	{
	    scheduler = Executors.newSingleThreadScheduledExecutor();

	    scheduler.scheduleAtFixedRate(() -> {
	        if (inicializado)
	        {
	            cont++;
	            if(cont == 4)
	            {
	                cont = 0;
	                moverEnem();
	            }

	            ListaNaves.getListaNaves().moverDisp();
	        }
	    }, 0, 100, TimeUnit.MILLISECONDS);

		Espacio.getEspacio().addObserver(this);	
	}
		
	public static ListaEnem getListaEnem()
	{
		if(miListaEnem == null)
		{
			miListaEnem = new ListaEnem();
		}
		return miListaEnem;
	}
	private Enemigo fabricarEnemigos(int[] posiciones)
	{
		// llamamos al factory indicando tipo 1 para Enemigo, null en color y las posiciones
		return (Enemigo) Factory.getFactory().generar(1, null, posiciones);
	}
	public void inicializar()
	{
		LEnems = new ArrayList<Enemigo>();
		int cantEnem = new Random().nextInt(4,9);
		int dist=90/cantEnem;
		/*          Comporobación de dos enem colisionados por el mismo disp
		cantEnem=2;
		ArrayList<int[]> coor = new ArrayList<>();
		coor.add(new int[] {2,15});
		coor.add(new int[] {2,19});
		*/
		for(int i=0;i<cantEnem;i++)
		{
			int[] pos = {2,5+i*dist}; 
			//int[] pos = coor.get(i);      Parte de la comprobación de 2 enem 1 disp
			LEnems.add(fabricarEnemigos(pos));
		};
		for(Enemigo e: LEnems)
		{
			e.crear();
		}
		inicializado = true;
	}
	private void removeEnem(int x, int y)//LLamado por el update
	{
		int i=0;
		boolean enc = false;
		while(i<LEnems.size() && !enc)
		{
			Enemigo enem = LEnems.get(i);
			enc = enem.encontrar(x, y);
			if(enc)
			{
				enem.borrar();
				LEnems.remove(i);
				i--;
			}
			i++;
			
		}
		if(LEnems.size()==0)
		{
			Espacio.getEspacio().notifyFin(1);//Anunciar victoria
		}
	}
	public void moverEnem() //version postLabo
	{
		for (Enemigo e : new ArrayList<>(LEnems))
		{
		    e.mover();
		}
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		Object[] res = (Object[]) arg;//arg: destinatario,tablero,estado,juegoInic,finJuego,color,accion,coordenadas
		int destinatario = (int) res[0];
		if(destinatario == 3)//Si va dirigido a LE
		{
			int[] coor = (int[]) res[6];
			int x = coor[0];
			int y = coor[1];
			removeEnem(x, y);
		}
		
	}
}
