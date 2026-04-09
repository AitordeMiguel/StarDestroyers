package model;

import java.util.ArrayList;

public class DispFlecha implements StrategyDisp{

	@Override
	public Composite crearDisp(int x, int y) {
		Composite comp = null;
		
		PixelD d1 = new PixelD(x-1,y);//Arriba
		PixelD d2 = new PixelD(x,y-1);//Izq
		PixelD d3 = new PixelD(x,y+1);//Der
		
		ArrayList<PixelD> pixeles = new ArrayList<PixelD>();
		pixeles.add(d1);
		pixeles.add(d2);
		pixeles.add(d3);
		boolean creable = true;
		int i = 0;
		while(i<pixeles.size() && creable)
		{
			PixelD d = pixeles.get(i);
			creable = d.comprobarCrear();
			i++;
		}
		if(creable)//Si todos se han podido crear
		{
			comp = new Composite();
			for(PixelD d: pixeles){comp.addComponente(d);}
		}
		
		return comp;
	}

}
