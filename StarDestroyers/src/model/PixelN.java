package model;

public class PixelN implements Component{
	private int x,y;
	public PixelN(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}
	@Override
	public boolean mover(String dir) 
	{
			if(dir.equals("up")) {//Actualiza coordenadas 
				x--;
			} else if(dir.equals("down")) { 
				x++;
			} else if(dir.equals("left")) {
				y--;
			} else if(dir.equals("right")) {
				y++;
			}
			return false; //False pues no se usa el return aquí, solo en composite y pixelD
	}

	@Override
	public void crear(int accion) {
		Espacio.getEspacio().dibujarNave(new int[]{x, y},accion);
	}

	@Override
	public void borrar() {
		Espacio.getEspacio().desdibujar(new int[]{x, y});
		
	}
	@Override
	public boolean comprobarMover(String dir) {
		return Espacio.getEspacio().comprobarMoverNave(x, y, dir);
	}
	
	@Override
	public boolean encontrar(int x, int y) {//Método de composite, y los otros píxeles
		return false;
	}
	@Override
	public void notificar(int dest, int estado, String color, int accion, int tipo) 
	{
		Espacio.getEspacio().notificar(dest, estado, color, new int[] {x,y}, accion, tipo);
	}

}
