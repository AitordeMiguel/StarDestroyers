package model;

public class PixelN implements Component{
	private int x,y;
	public PixelN(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}
	@Override
	public void mover(String dir) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean comprobarMover(String dir) {
		// TODO Auto-generated method stub
		return false;
	}

}
