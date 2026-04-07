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
