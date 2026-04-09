package model;

public class PixelN implements Component{
	private int x,y;
	public PixelN(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}
	@Override
	public void mover(String dir) 
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
			//espacio dibuja la nave en esta nueva posición
			Espacio.getEspacio().moverNave(new int[]{x, y});
	}

	@Override
	public void crear() {
		Espacio.getEspacio().crearNave(new int[]{x, y});
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
	public boolean comprobarCrear() {
		// TODO de momento solo creamos al princicio, que no da problemas, por lo que no lo necesitamos
		return false;
	}
	@Override
	public boolean encontrar(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
