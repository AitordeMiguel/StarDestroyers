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
			}
		}
	}

	@Override
	public void crear() {
		Iterator<Component> it = components.iterator();
		while(it.hasNext()){
		    Component comp = it.next();
		    comp.crear();
		}
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
	public boolean comprobarCrear() {
		// TODO Auto-generated method stub
		return false;
	}

}
