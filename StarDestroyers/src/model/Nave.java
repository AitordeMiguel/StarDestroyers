package model;

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
}
