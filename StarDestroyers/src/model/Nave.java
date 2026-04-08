package model;

import java.util.ArrayList;

public abstract class Nave extends PiezaAbs{
	protected int cantR;
	protected int cantF;
	protected String color;
	protected ArrayList<Disparo> LDis;
	protected Composite forma;
	    
	public Nave(String color, int cantR, int cantF, Composite forma) {
		this.color = color;
	    this.cantR = cantR;
	    this.cantF = cantF;
	    this.forma = forma;
	    this.LDis = new ArrayList<Disparo>();
	    }
	
	public void disparar()//Crear Disparo
	{//TODO tiene que ser en las hijas a menos que todos igual
		/*
		//LLamar a la estrategia que toque
		Disparo disp; //TODO que esto llame y obtenga el disparo creado
		if(disp != null)
		{
			LDis.add(disp);
		}
		*/
	}
	
	public void dibujar() 
	{
		forma.crear();
	}
	
	public void mover(String dir)
	{
		forma.mover(dir);
	}
    } 

	

