package model;

import java.util.ArrayList;

public abstract class PiezaAbs {
	protected ArrayList<Disparo> LDis;
	protected Composite forma;
	protected StrategyDisp sD;
	protected int tipoDisparoActual = 1;
	
	protected PiezaAbs(Composite pForma)
	{
		this.forma = pForma;
		this.sD = new DispNormal();
		this.LDis = new ArrayList<Disparo>();
	}
	public void mover(String dir)
	{
		forma.mover(dir);
	}
	public void borrar()
	{
		forma.borrar();
	}
	public void crear()//Solo se llama al inicializar
	{
		forma.crear(0);//0 indica que es inicializar
	}
}
