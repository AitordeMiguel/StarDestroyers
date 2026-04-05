package model;

import java.util.ArrayList;

public class NaveV extends Nave{
	private int cantR, cantF;
	private ArrayList<Disparo> LDis;
	
	public NaveV(Composite formaComp)
	{
		cantR = 0;		
		cantF = 30;
		LDis = new ArrayList<Disparo>();
	}
}
