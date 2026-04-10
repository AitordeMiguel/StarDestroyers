package model;

import java.util.*;//Importa todo lo de java.util  (ArrayList e Iterator para nosotros)

public class Composite implements Component{
	
	private ArrayList<Component> components = new ArrayList<Component>();
	
	public void addComponente(Component pF) 
	{
		components.add(pF);
	}
	public void delComponente(Component pF) 
	{
		components.remove(pF);
	}
	    
	@Override
	public boolean comprobarMover(String dir) {
		Iterator<Component> itComprobar = components.iterator();
		boolean rdo = true;
		while(itComprobar.hasNext() && rdo){
		    Component comp = itComprobar.next();
		    rdo = comp.comprobarMover(dir);
		}
		return rdo;
	}
	@Override
	public void mover(String dir) { 
		
		if (this.comprobarMover(dir)) {
			// Desdibujar
			Iterator<Component> itBorrar = components.iterator();
			while(itBorrar.hasNext()){
			    Component comp = itBorrar.next();
			    comp.borrar();
			}
			// Mover y volver a dibujar
			Iterator<Component> itMover = components.iterator();
			while(itMover.hasNext()){
			    Component comp = itMover.next();
			    comp.mover(dir);
			}//En este punto ya se han pintado todas las nuevas posiciones
			Espacio.getEspacio().notificar(1, 2, null, null);
		}
	}

	@Override
	public void crear()//, int accion) 
	{
		for(Component c: components)
		{
			c.crear();
		}
		/*
		if(tipo==1)//Nave
		{
			if (accion == 0) {
				Espacio.getEspacio().crearNave(obtCoor());
			} else if (accion == 1) {
				Espacio.getEspacio().redibujarNave(obtCoor());
			}
		}
		else if(tipo==2)//Enemigo
		{
			if (accion == 0) {
				Espacio.getEspacio().crearEnem(obtCoor());
			} else if (accion == 1) {
				Espacio.getEspacio().redibujarEnem(obtCoor());
			}
		}
		else if(tipo==3)//Disparo
		{
			Espacio.getEspacio().crearDisp(obtCoor());
		}
		*/
	}

	@Override
	public void borrar() {
		Iterator<Component> it = components.iterator();
		while(it.hasNext()){
		    Component comp = it.next();
		    comp.borrar();
		}
	}
	@Override
	public boolean comprobarCrear() {//TODO solo se una en pixelD
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean encontrar(int x, int y) {
		boolean enc = false;
		Iterator<Component> it = components.iterator();
		while(it.hasNext() && ! enc){
		    Component comp = it.next();
		    enc = comp.encontrar(x, y);
		}
		return enc;
	}
	public ArrayList<int[]> obtCoor()//TODO, se usaba cuando pintabamos todo de golpe, puede que haya que borrarlo
	{
		ArrayList<int[]> coor = new ArrayList<int[]>();
		Iterator<Component> it = components.iterator();
		while(it.hasNext()){
		    Component comp = it.next();
		    coor.add(comp.getCoor());
		}
		return coor;
	}
	@Override
	public int[] getCoor() {
		// TODO Auto-generated method stub
		return null;
	}
}
