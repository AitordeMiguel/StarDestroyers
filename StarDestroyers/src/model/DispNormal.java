package model;

public class DispNormal implements StrategyDisp{

	@Override
	public Composite crearDisp(int x, int y) {
		Composite comp = null;
		PixelD d = new PixelD(x,y);
		if(d.comprobarCrear())//Si se va a poder crear este pixel
		{
			comp = new Composite();
			comp.addComponente(d);
		}//Si no se puede crear pues el composite se queda null, y como no se ha guardado el pixel, se pierde también
		return comp;
	}

}
