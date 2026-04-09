package model;

public class PixelE implements Component{
	private int x,y;
	public PixelE(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}

	@Override
	public void mover(String dir) {
		// TODO Auto-generated method stub
		x++;
		this.crear();
	}

	@Override
	public void crear() {
		Espacio.getEspacio().crearEnem(new int[]{x, y});
		
	}

	@Override
	public void borrar() {
		Espacio.getEspacio().desdibujar(new int[]{x, y});		
	}

	@Override
	public boolean comprobarMover(String dir) {
		boolean movido;
		int rdo = Espacio.getEspacio().comprobarMoverEnem(x, y);
		if(rdo == 1) {movido = true;}
		else
		{
			movido = false;
		}
		return movido;
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

}
