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
	public void crear() {
		Espacio.getEspacio().crearNave(new int[]{x, y});
	}
	
	@Override
	public void dibujar() //Durante la partida
	{
		Espacio.getEspacio().dibujarNave(new int[]{x, y});
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
		//De momento solo creamos al princicio, que no da problemas, por lo que no lo necesitamos
		return false;
	}
	@Override
	public boolean encontrar(int x, int y) {//Método de composite, y los otros píxeles
		return false;
	}
	@Override
	public int[] getCoor() {
		return new int[] {x,y};
	}

}
