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
	public void mover() {
		Iterator<Component> it = components.iterator();
		while(it.hasNext()){
		    Component comp = it.next();
		    comp.mover();
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

}
