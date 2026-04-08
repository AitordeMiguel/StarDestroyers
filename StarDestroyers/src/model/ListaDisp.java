package model;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ListaDisp{
	private ArrayList<int[]> LDis;
	private static ListaDisp miListaDisp;
	private Timer timer = null;
	private int cont=0;
	private boolean inicializado,fin=false;
	
	private ListaDisp()
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if(inicializado &&!fin) {
					moverDisp();
					moverEnem();
				}
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 50);
	}
	
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
		ListaNaves.getListaNaves().inicializar(color);
		inicializado=true;
	}
	private void addDisp(int x, int y)
	{
		int[] coor = {x,y};
		LDis.add(coor);
	}
	private void removeDisp(int x, int y)//TODO tener cuidado cuando sean disparos multi-pixel, pues habrá que cambiarlo
	{
		for(int i=0;i<LDis.size();i++)
		{
			if(LDis.get(i)[0]==x && LDis.get(i)[1]==y)
			{
				LDis.remove(i);
			}
		}
	}
	
	private void moverDisp()
	{
		ArrayList<int[]> rdo = ListaNaves.getListaNaves().moverDisp(LDis);
		for (int i=0;i<rdo.size();i++)
		{
			if(rdo.get(i)[0]==1)//si se ha movido
			{
				LDis.get(i)[0]--;//sube una pos
			}
			else//si no se ha movido, o sea, ha chocado con un enemigo o ha llegado al borde
			{
				LDis.remove(i);
				rdo.remove(i);
				i--;//para no saltarnos el siguiente disparo
			}
		}
	}
	public void crearDisp(String tipo)
	{
		//primer elem: 1=creado  0=no creado
		//segundo y tercero: posX, posY
		//int[] sol = ListaNaves.getListaNaves().crearDisp(tipo);
		//if(sol[0]==1) {addDisp(sol[1],sol[2]);}
	}
	private void moverEnem() {
		cont++;
		if (cont >= 4) 
		{
			//System.out.println("LLamado");
			ArrayList<int []> rdo =ListaNaves.getListaNaves().moverEnem(); 
			for(int i=0; i<rdo.size(); i++) 
			{
				if (rdo.get(i)[1]==1)
				{
					//si se elimina disp
					removeDisp(rdo.get(i)[2],rdo.get(i)[3]);
				}
				if(rdo.get(i)[0]==2)//ha perdido
				{
					fin =true;//Se ha perdido
				}
			}
			cont = 0; 
		}
		
	}
}
