package model;

import java.awt.Color;

public class Nave extends Casilla{
	private int cantR, cantF;
	private String color;
	
	public Nave(String pColor)
	{
		color = pColor;
		if(color=="green")
		{
			cantR = 0;		
			cantF = 30;
			setBackground(Color.GREEN);
		}
		else if(color=="blue")
		{
			cantR = 20;		
			cantF = 0;	
			setBackground(Color.BLUE);
		}
		else
		{
			cantR = 20;		
			cantF = 30;
			setBackground(Color.RED);
		}
	}
}
