package model;

public class PixelD  implements Component{
	private int x,y;
	public PixelD(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}

	@Override
	public boolean mover(String dir) {//TODO el rdo dice si se borra el pixel por haber llegado arriba
		// El disparo del jugador siempre va hacia arriba (x disminuye)
		boolean rdo = false;
		x--;
		if(x<0)//Si ya se ha salido de la pantalla
		{
			rdo = true;
		}
		//this.crear();    TODO, si al final se hace por pixeles volver a ponerlo
		return rdo;
	}

	@Override
	public void crear() {
		Espacio.getEspacio().dibujarDisp(new int[]{x, y});
	}

	@Override
	public void borrar() {
		Espacio.getEspacio().desdibujar(new int[]{x, y});
	}

	@Override
	public boolean comprobarMover(String dir) {
		return Espacio.getEspacio().comprobarMoverDisp(x, y);
	}

	@Override
	public boolean comprobarCrear() {
		return Espacio.getEspacio().comprobarCrearDisp(x, y);
	}

	@Override
	public boolean encontrar(int pX, int pY) {
		return (pX == this.x) && (pY == this.y);
	}

	@Override
	public int[] getCoor() {// TODO Es probable que no se use al final
		return new int[] {x,y};
	}
	

}
