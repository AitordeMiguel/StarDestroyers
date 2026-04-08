package viewController;

import java.awt.EventQueue;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Espacio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import java.util.Timer;
import java.util.TimerTask;

import java.awt.Dimension;
import java.awt.Toolkit;


@SuppressWarnings("deprecation")
public class Juego extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel[][] tablero;
	private Controlador controlador;
	private String colorN; 

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Juego(String colorNave, int[][] mat) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setExtendedState(JFrame.MAXIMIZED_BOTH);	//inicializar a pantalla completa.
		
		colorN = colorNave;
		
		ImageIcon EspacioOriginal = new ImageIcon(getClass().getResource("espacio.jpg"));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Image imagenEscalada = EspacioOriginal.getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_SMOOTH);
		JLabel lblFondo = new JLabel(new ImageIcon(imagenEscalada));

			
		lblFondo.setLayout(new BorderLayout());  
		setContentPane(lblFondo);  
		lblFondo.add(getPanel(mat), BorderLayout.CENTER);
		// Inicializar el controlador y asignarlo
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(getControlador());

		// Agregar este frame como observer del modelo
		Espacio.getEspacio().addObserver(this);	
	}
	
	private JPanel getPanel(int[][] tabEsp) {//tabEsp:  0=nave 1=disp 2=enem 3=vacío
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new GridLayout(60, 100, 0, 0));
			JLabel lblNewLabel;
			tablero = new JLabel[60][100];
			for(int f=0;f<60;f++)
			{
				for(int c=0;c<100;c++)
				{
					lblNewLabel = new JLabel("");
					if(tabEsp[f][c]==0)//es la nave
					{
						lblNewLabel.setOpaque(true);
						if(colorN.equals("green")) {
							lblNewLabel.setBackground(Color.GREEN);
						}
						else if(colorN.equals("blue")) {
							lblNewLabel.setBackground(Color.BLUE);
						}
						else {
							lblNewLabel.setBackground(Color.RED);
						}
					}
					else if(tabEsp[f][c]==1)//es un disparo
					{
						lblNewLabel.setOpaque(true);
					    lblNewLabel.setBackground(Color.YELLOW);
					}
					else if(tabEsp[f][c]==2)//es un enemigo
					{
						lblNewLabel.setOpaque(true);
					    lblNewLabel.setBackground(Color.GRAY);
					}
					else//if(tabEsp[f][c]==3) es espacio vacio
					{
						lblNewLabel.setOpaque(false);
					}
					
					panel.add(lblNewLabel);
					tablero[f][c] = lblNewLabel;
				}
			}
			
		}
		return panel;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Object[] res = (Object[]) arg;//arg: destinatario,tablero,estado,juegoInic,finJuego,color
		if((int) res[0]==1)//Si está dirigido al juego
		{
			boolean terminado = (boolean) res[4];
			boolean iniciado = (boolean) res[3];
			if(!terminado && iniciado)//Creo que con el destinatario esto sobrará, pero ya veremos
			{
				int estado = (int) res[2];
				if(estado != 2)//Se pierde o gana
				{
					this.setVisible(false);
				    Fin fin = new Fin(estado);
				    fin.setVisible(true);
				}
				else
				{
					int[][] tabNum = (int[][]) res[1];
					for(int f=0;f<60;f++)
					{
						for(int c=0;c<100;c++)
						{
							JLabel lbl = tablero[f][c];
							if(tabNum[f][c]==0)//Si es nave
							{
								lbl.setOpaque(true);
								
								if(colorN.equals("green")) {
									lbl.setBackground(Color.GREEN);
								}
								else if(colorN.equals("blue")) {
									lbl.setBackground(Color.BLUE);
								}
								else {
									lbl.setBackground(Color.RED);
								}
								lbl.repaint();
							}
							else if(tabNum[f][c]==1)//Si es disparo
							{
								lbl.setOpaque(true);
								lbl.setBackground(Color.YELLOW);
								lbl.repaint();
							}
							else if(tabNum[f][c]==2)//Si es un enemigo
							{
								lbl.setOpaque(true);
								lbl.setBackground(Color.GRAY);
								lbl.repaint();
							}
							else if(tabNum[f][c]==3)//Si es un vacío
							{
								lbl.setOpaque(false);
								lbl.repaint();
							}
						}
					}
						
				}
			}
		}
		/*
		Object[] res = (Object[]) arg;//arg: acción,posAnt,posNueva,tipo
		int accion = (int) res[0];
		int[] posAnt = (int[]) res[1];
		int[] posNueva = (int[]) res[2];
		int tipo = (int) res[3];
		int estado = (int) res[4];
		
		int fN=posNueva[0];
		int cN=posNueva[1];
		int fA=posAnt[0];
		int cA=posAnt[1];
		boolean terminado = (boolean) res[8];
		boolean iniciado = (boolean) res[5];
		if(!terminado && iniciado)//Si no ha terminado el juego, y se ha empezado el juego
		{
			if (estado != 2) {//0=perder, 1=ganar 
			    this.setVisible(false);
			    Fin fin = new Fin(estado);
			    fin.setVisible(true);
			    terminado = true;
			}
			else
			{
				if(accion==0)//se quiere mover
				{
					JLabel lblA = tablero[fA][cA];
					JLabel lblN = tablero[fN][cN];
					//borrar la casilla antigua
					lblA.setOpaque(false);
					lblA.repaint();
					//dibujar la nueva
					lblN.setOpaque(true);
					lblN.repaint();
					if(tipo==0)//lo que se mueve es nave
					{
						if(colorN.equals("green")) {
							lblN.setBackground(Color.GREEN);
						}
						else if(colorN.equals("blue")) {
							lblN.setBackground(Color.BLUE);
						}
						else {
							lblN.setBackground(Color.RED);
						}
					}
					else if(tipo==1)//lo que se mueve es disparo
					{
						lblN.setBackground(Color.YELLOW);
					}
					else//if (tipo==2) vamos, que es enemigo
					{
						lblN.setBackground(Color.GRAY);
					}
					
				}
				else if(accion==1)//se borra algo
				{
					JLabel lblA = tablero[fA][cA];
					lblA.setOpaque(false);
					lblA.repaint();
				}
				else if(accion==2) //se crea algo nuevo    
				{
					JLabel lblN = tablero[fN][cN];
					lblN.setOpaque(true);
					if(tipo==0)//lo que se crea es nave, aunque no deberían poder crearse más naves
					{
						if(colorN.equals("green")) {
							lblN.setBackground(Color.GREEN);
						}
						else if(colorN.equals("blue")) {
							lblN.setBackground(Color.BLUE);
						}
						else {
							lblN.setBackground(Color.RED);
						}
					}
					else if(tipo==1)//lo que se crea es disparo
					{
						lblN.setBackground(Color.YELLOW);
					}
					else//if (tipo==2) vamos, que es enemigo, aunque no deberían poder crearse más enemigos
					{
						lblN.setBackground(Color.GRAY);
					}
				}
				else if (accion==4) //borrar 2, disparoEnemigo o NaveEnemigo
				{
					JLabel lblA = tablero[fA][cA];
					JLabel lblN = tablero[fN][cN];
					lblA.setOpaque(false);
					lblA.repaint();
					lblN.setOpaque(false);
					lblN.repaint();
					
				}
			}
		}
		
		*/
	}

	// Instancia del controlador
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	// Clase interna Controlador
	private class Controlador implements KeyListener
	{	
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {model.ListaNaves.getListaNaves().moverNave("left");}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {model.ListaNaves.getListaNaves().moverNave("right");}
			if (e.getKeyCode() == KeyEvent.VK_UP) {model.ListaNaves.getListaNaves().moverNave("up");}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {model.ListaNaves.getListaNaves().moverNave("down");}
			
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {model.ListaDisp.getListaDisp().crearDisp("normal");}
			
			if (e.getKeyCode() == KeyEvent.VK_C) {}//TODO Cambiar tipo disparo
			
			if (e.getKeyCode() == KeyEvent.VK_P) {model.Espacio.getEspacio().notificar();}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

}
