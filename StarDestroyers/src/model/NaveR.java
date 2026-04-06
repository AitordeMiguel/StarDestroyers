package model;

import java.util.ArrayList;

public class NaveR extends Nave{
	private int cantR, cantF;
	private ArrayList<Disparo> LDis;
	
	public NaveR(Composite formaComp)
	{
		cantR = 20;		
		cantF = 30;
		LDis = new ArrayList<Disparo>();
	}
}
