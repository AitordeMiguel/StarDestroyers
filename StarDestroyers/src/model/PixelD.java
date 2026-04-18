package model;

public class PixelD  implements Component{
	private int x,y;
	public PixelD(int pX, int pY)
	{
		this.x = pX;
		this.y = pY;
	}

	@Override
	public boolean mover(String dir) {//El rdo dice si se borra el pixel por haber llegado arriba
		// El disparo del jugador siempre va hacia arriba (x disminuye)
		boolean rdo = false;
		x--;
		if(x<0)//Si ya se ha salido de la pantalla
		{
			rdo = true;
		}
		return rdo;
	}

	@Override
	public void crear(int accion) //Nunca será inicializar, siempre 1
	{
		Espacio.getEspacio().dibujarDisp(new int[] {x,y});
	}
	

	@Override
	public void borrar() {
		Espacio.getEspacio().desdibujar(new int[]{x, y});
	}

	@Override
	public boolean comprobarMover(String dir) {
		return Espacio.getEspacio().comprobarMoverDisp(x, y);
	}

	public boolean comprobarCrear() {
		return Espacio.getEspacio().comprobarCrearDisp(x, y);
	}

	@Override
	public boolean encontrar(int pX, int pY) {
		return (pX == this.x) && (pY == this.y);
	}

	@Override
	public void notificar(int dest, int estado, String color, int accion, int tipo) 
	{
		Espacio.getEspacio().notificar(dest, estado, color, new int[] {x,y}, accion, tipo);
	}

	

}
