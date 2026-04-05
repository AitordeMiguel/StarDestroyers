package model;

import java.util.ArrayList;

public class NaveA extends Nave{
	private int cantR, cantF;
	private ArrayList<Disparo> LDis;
	
	public NaveA(Composite formaComp)
	{
		cantR = 20;		
		cantF = 0;
		LDis = new ArrayList<Disparo>();
	}
}
