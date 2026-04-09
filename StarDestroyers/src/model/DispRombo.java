package model;

import java.util.ArrayList;

public class DispRombo implements StrategyDisp{

	@Override
	public Composite crearDisp(int x, int y) {
		Composite comp = null;
		int[][] offsets = {
			    {0,0},                 // d1

			    {-1,-1}, {-1,0}, {-1,1},  // d2-d4

			    {-2,-2}, {-2,-1}, {-2,0}, {-2,1}, {-2,2}, // d5-d9

			    {-3,-1}, {-3,0}, {-3,1},  // d10-d12

			    {-4,0}                 // d13
			};
		
		ArrayList<PixelD> pixeles = new ArrayList<>();
		for (int[] o : offsets) {
		    pixeles.add(new PixelD(x + o[0], y + o[1]));
		}
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
