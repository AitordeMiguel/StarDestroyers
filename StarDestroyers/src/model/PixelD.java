package model;

public class PixelD  implements Component{
	private int x,y;
	public PixelD(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}

	@Override
	public void mover(String dir) {
		// El disparo del jugador siempre va hacia arriba (x disminuye)
		if(dir.equals("up")) {
			x--;
		}
		this.crear();
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
