package model;

import java.util.ArrayList;

public abstract class Nave extends PiezaAbs{
	protected int cantR;
	protected int cantF;
	protected String color;
	protected ArrayList<Disparo> LDis;
	protected Composite forma;
	protected StrategyDisp sD;
	protected int x,y;//Son las coordenadas de referencia
	    
	public Nave(String color, int cantR, int cantF, Composite forma) {
		this.color = color;
	    this.cantR = cantR;
	    this.cantF = cantF;
	    this.forma = forma;
	    this.LDis = new ArrayList<Disparo>();
	    this.sD = new DispNormal();
	    }
	
	public void disparar()//Crear Disparo
	{
		Composite formaDisp = sD.crearDisp(x, y);
		if(formaDisp != null)//Será null si no se ha podido crear
		{
			Disparo disp  = new Disparo(formaDisp);
			LDis.add(disp);
			disp.dibujar();
		}
	}
	
	public void dibujar() 
	{
		forma.crear(1);//tipo nave
	}
	
	public void mover(String dir)
	{
		forma.mover(dir);
	}
	
	public void cambiarStrategy(int nueva)
	{
		if(nueva==1)//Normal
		{
			sD = new DispNormal();
		}
		else if(nueva==2)//Flecha
		{
			sD = new DispFlecha();
		}
		else if(nueva==3)//Rombo
		{
			sD = new DispRombo();
		}
	}
	
    } 

	

