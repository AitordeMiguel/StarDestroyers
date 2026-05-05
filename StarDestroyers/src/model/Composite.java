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
	public boolean mover(String dir) 
	{ 
		boolean rdo = this.comprobarMover(dir);
		if (rdo) 
		{
			// Desdibujar
			this.borrar();//Esto notifica ya a Juego de quitar cada pixel
			// Mover
			for(int i=0; i<components.size();i++)//TODO java8?  --> copiar si es válido el de Nave   --> probar
			{
				Component comp = components.get(i);
			    if(comp.mover(dir))//Solo actualiza posiciones
			    {//Solo con disparo
			    	components.remove(i);
			    	i--;
			    }
			}
			//Redibujar
			this.crear(1);//Esto ya ha notificado a Juego que debe pintar cada pos
			//En este punto ya se han pintado todas las nuevas posiciones
			
		}
		return rdo;//Solo lo usa la nave, creo
	}

	@Override
	public void crear(int accion) //accion: 0= inic, 1=partida
	{ //Este método puede notificar internamente al Juego
		//Java8
		components.stream().forEach(c -> c.crear(accion));
		//Antiguo
		/*
		for(Component c: components)//TODO java8
		{
			c.crear(accion);
		}
		*/
	}
	

	@Override
	public void borrar() {//Este método notifica internamente al Juego
		//java8
		components.stream().forEach(c -> c.borrar());
		//antiguo
		/*
		Iterator<Component> it = components.iterator();
		while(it.hasNext()){//TODO java 8
		    Component comp = it.next();
		    comp.borrar();
		}
		*/
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
	
	@Override
	public void notificar(int dest, int estado, String color, int accion, int tipo)
	{
		for(Component c: components)
		{
			c.notificar(dest, estado, color, accion, tipo);
		}
		
	}
		
	public int tamRestante()
	{
		return components.size();
	}
	
}
