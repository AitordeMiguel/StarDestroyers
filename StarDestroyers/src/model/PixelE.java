package model;

public class PixelE implements Component{
	private int x,y;
	public PixelE(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}

	@Override
	public boolean mover(String dir) {
		x++;
		//this.crear();  TODO, si al final se hace por pixeles volver a ponerlo
		return false; //False pues no se usa el return aquí, solo en composite
	}

	@Override
	public void crear() {
		Espacio.getEspacio().dibujarEnem(new int[]{x,y});
	}

	@Override
	public void borrar() {
		Espacio.getEspacio().desdibujar(new int[]{x, y});		
	}

	@Override
	public boolean comprobarMover(String dir) {
		return Espacio.getEspacio().comprobarMoverEnem(x, y);
	}

	@Override
	public boolean comprobarCrear() {
		// TODO de momento solo creamos al princicio, que no da problemas, por lo que no lo necesitamos
		return false;
	}

	@Override
	public boolean encontrar(int pX, int pY) {
		return (pX == this.x) && (pY == this.y);
	}

	@Override
	public int[] getCoor() {
		return new int[] {x,y};
	}


}
