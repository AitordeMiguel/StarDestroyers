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
    } 
	/*
	private int cantR, cantF;
	private String color;
	
	public Nave(String pColor)
	{
		color = pColor;
		if(color=="green")
		{
			cantR = 0;		
			cantF = 30;
		}
		else if(color=="blue")
		{
			cantR = 20;		
			cantF = 0;	
		}
		else
		{
			cantR = 20;		
			cantF = 30;
		}
	}
	
	public String getColor() {
		return color;
	}
	*/

