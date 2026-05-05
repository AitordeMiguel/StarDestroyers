package model;

import java.util.ArrayList;

//import java.util.ArrayList;
//import java.util.Iterator;

public abstract class Nave extends PiezaAbs{
	protected int cantR;
	protected int cantF;
	protected String color;
	//protected ArrayList<Disparo> LDis;
	//protected Composite forma;
	//protected StrategyDisp sD;
	protected int x,y;//Son las coordenadas de referencia, es la punta
	//protected int tipoDisparoActual = 1; //atributo para saber qué arma está seleccionada (1=Normal, 2=Flecha, 3=Rombo)
	    
	public Nave(String color, int cantR, int cantF, Composite forma) {
		super(forma);
		this.color = color;
	    this.cantR = cantR;
	    this.cantF = cantF;
	    //this.forma = forma;
	    //this.LDis = new ArrayList<Disparo>();
	    //this.sD = new DispNormal();
	    this.x = 55;                 //TODO límites del tablero
	    this.y = 50;
	    }
	
	public int[] disparar()//Crear Disparo
	{
		int[] rdo = new int[3];
		rdo[0] = 0;//No se ha creado
		rdo[1]=0;//Tipo normal
		Composite formaDisp = sD.crearDisp(x-2, y);
		if(formaDisp != null)//Será null si no se ha podido crear
		{
			Disparo disp  = new Disparo(formaDisp);
			LDis.add(disp);
			disp.dibujar(); //Este notifica internamente a Juego para que pinte
			// Gastar munición según el arma actual
			if (tipoDisparoActual == 2) {
				cantF--;
				rdo[1]=1;//Tipo flecha
				rdo[2]=cantF;
				if (cantF <= 0) cambiarStrategy(1); //se acaba Flecha, vuelve a Normal
			} else if (tipoDisparoActual == 3) {
				cantR--;
				rdo[1]=2;//Tipo rombo
				rdo[2]=cantR;
				if (cantR <= 0) cambiarStrategy(1); //se acaba Rombo, vuelve a Normal
			}
			rdo[0] = 1;//Sí se ha creado
		}
		return rdo;
	}
	
	
	
	@Override
	public void mover(String dir)
	{
		if(forma.mover(dir))//Si se ha movido
		{//Actualiza coor de referencia
			if(dir.equals("up")) x--;
			else if(dir.equals("down")) x++;
			else if(dir.equals("left")) y--;
			else if(dir.equals("right")) y++;
		}
	}
	
	public boolean cambiarStrategy(int nueva) {//çno lo pongo en la madre pues no creemos que si el enemigo llega a tener disparos, vaya a tener iguales
		boolean rdo = false; //No se ha podido cambiar, si se puede ya se pondrá true
		if(nueva == 1) {
			sD = new DispNormal();
			tipoDisparoActual = 1;
			rdo = true;
		}
		else if(nueva == 2 && cantF > 0) {
			if(cantF>0)
			{
				sD = new DispFlecha();
				tipoDisparoActual = 2;
				rdo = true;
			}
		}
		else if(nueva == 3 && cantR > 0) {
			if(cantR>0)
			{
				sD = new DispRombo();
				tipoDisparoActual = 3;
				rdo = true;
			}
		}
		return rdo;
	}
	public void moverDisp()//TODO java8?
	{
		//Con java8
		//ArrayList<Disparo> LD = new ArrayList<Disparo>(LDis);
		//LD.stream().filter(disp -> disp.mover()).forEach(d -> LDis.remove(d));
		//Antiguo
		for(int i=0;i<LDis.size();i++)
		{
			Disparo d = LDis.get(i);
			if(d.mover())
			{
				LDis.remove(i);//Borrarlo pues no queda ningún pixel
				i--;
			}
		}
	}
	
	public void borrarDisp(int[] coor)
	{
		boolean borrado = false;
		int i = 0;
		while(!borrado && i<LDis.size())
		{
			borrado = LDis.get(i).encontrar(coor);
			if(borrado)
			{
				LDis.remove(i);//Borrarlo de la lista
			}
			i++;
		}
	}
	
} 

	

