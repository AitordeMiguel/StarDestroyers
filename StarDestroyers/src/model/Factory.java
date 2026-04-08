package model;
import java.util.*;

public class Factory {
	private static Factory miFactory;
    private Factory(){}
    
    public static Factory getFactory()
    {
    	if (miFactory == null){miFactory = new Factory();}
    	return miFactory;
    }
    public PiezaAbs generar(int tipo, String col, int[] LPos)
    {
    	PiezaAbs elem = null;
    	if(LPos != null)
    	{
    		if(tipo==0) //Queremos nave
        	{
        		int x = LPos[0];
        		int y = LPos[1];
        		elem = generarNave(col,x,y);
        	}
        	else {elem = generarEnem(LPos);}//Queremos enemigo
    	}
    	return elem;
    }
    
    private Nave generarNave(String color, int x, int y)
    {//TODO si queremos que se puedan regenerar naves, hay que comprobar que se pueda crear en el sitio sin que haya otra cosa
    	Nave nave = null;
    	
    	if(x>0 && x<=59 && y>0 && y<99) //Si la nave cabe en el plano
    	{
    		Composite formaComp = new Composite();
    		
    		PixelN n1 = new PixelN(x,y);//Centro
    		PixelN n2 = new PixelN(x+1,y);//Derecha
    		PixelN n3 = new PixelN(x-1,y);//Izquierda
    		PixelN n4 = new PixelN(x,y-1);//Arriba
    		
    		formaComp.addComponente(n1);
    		formaComp.addComponente(n2);
    		formaComp.addComponente(n3);
    		formaComp.addComponente(n4);
    		if (color.equals("red")) {nave = new NaveR(formaComp);}
        	else if (color.equals("blue")) {nave = new NaveA(formaComp);}
        	else if (color.equals("green")) {nave = new NaveV(formaComp);}
    	}
    	
    	return nave;
    }
    
    private Enemigo generarEnem(int[] coor)
    {//TODO si queremos que se puedan regenerar naves, hay que comprobar que se pueda crear en el sitio sin que haya otra cosa
    	Enemigo enem = null;
    	
    	Composite formaComp = new Composite();
    	
    	int x = coor[0];
		int y = coor[1];
		
		PixelN n1 = new PixelN(x,y);//Centro
		PixelN n2 = new PixelN(x+1,y);//Derecha
		PixelN n3 = new PixelN(x-1,y);//Izquierda
		PixelN n4 = new PixelN(x,y+1);//Abajo
		
		formaComp.addComponente(n1);
		formaComp.addComponente(n2);
		formaComp.addComponente(n3);
		formaComp.addComponente(n4);
		enem = new Enemigo();
    	
    	return enem;
    }
}
