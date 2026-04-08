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
		//TODO de momento no lo queremos pero podría ser que llegue el día en que se pueda crear durante la partida y sí queramos
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

}
