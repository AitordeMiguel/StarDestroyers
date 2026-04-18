package model;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Nave extends PiezaAbs{
	protected int cantR;
	protected int cantF;
	protected String color;
	protected ArrayList<Disparo> LDis;
	protected Composite forma;
	protected StrategyDisp sD;
	protected int x,y;//Son las coordenadas de referencia, es la punta
	protected int tipoDisparoActual = 1; //atributo para saber qué arma está seleccionada (1=Normal, 2=Flecha, 3=Rombo)
	    
	public Nave(String color, int cantR, int cantF, Composite forma) {
		this.color = color;
	    this.cantR = cantR;
	    this.cantF = cantF;
	    this.forma = forma;
	    this.LDis = new ArrayList<Disparo>();
	    this.sD = new DispNormal();
	    this.x = 54;
	    this.y = 50;
	    }
	
	public void disparar()//Crear Disparo
	{
		Composite formaDisp = sD.crearDisp(x-2, y);
		if(formaDisp != null)//Será null si no se ha podido crear
		{
			Disparo disp  = new Disparo(formaDisp);
			LDis.add(disp);
			disp.dibujar(); //Este notifica internamente a Juego para que pinte
			// Gastar munición según el arma actual
			if (tipoDisparoActual == 2) {
				cantF--;
				if (cantF <= 0) cambiarStrategy(1); //se acaba Flecha, vuelve a Normal
			} else if (tipoDisparoActual == 3) {
				cantR--;
				if (cantR <= 0) cambiarStrategy(1); //se acaba Rombo, vuelve a Normal
			}
		}
	}
	
	public void crear()//Solo se llama al inicializar
	{
		forma.crear(0);//0 indica que es inicializar
	}
	
	public void mover(String dir)
	{
		if(forma.mover(dir))//Si se ha movido
		{//Actualiza coor de referencia
			if(dir.equals("up")) x--;
			else if(dir.equals("down")) x++;
			else if(dir.equals("left")) y--;
			else if(dir.equals("right")) y++;
		}
	}
	
	public void cambiarStrategy(int nueva) {
		if(nueva == 1) {
			sD = new DispNormal();
			tipoDisparoActual = 1;
		}
		else if(nueva == 2 && cantF > 0) {
			if(cantF>0)
			{
				sD = new DispFlecha();
				tipoDisparoActual = 2;
			}
		}
		else if(nueva == 3 && cantR > 0) {
			if(cantR>0)
			{
				sD = new DispRombo();
				tipoDisparoActual = 3;
			}
		}
	}
	public void moverDisp()
	{
		for(int i=0;i<LDis.size();i++)
		{
			Disparo d = LDis.get(i);
			if(d.mover())
			{
				LDis.remove(i);//Borrarlo pues no queda ningún pixel
				i--;
			}
		}
	}
	
	public void borrarDisp(int[] coor)
	{
		boolean borrado = false;
		int i = 0;
		while(!borrado && i<LDis.size())
		{
			borrado = LDis.get(i).encontrar(coor);
			if(borrado)
			{
				LDis.remove(i);//Borrarlo de la lista
			}
			i++;
		}
	}
	public void borrar()
	{
		forma.borrar();
	}
} 

	

