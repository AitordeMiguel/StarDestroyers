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

import model.Casilla;
import model.Disparo;
import model.Enemigo;
import model.Espacio;
import model.ListaEnem;
import model.Nave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class Juego extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel[][] tablero;
	private Controlador controlador;
	private Timer timer = null;
	private int vueltas;
	private boolean win,loose = false;
	private String colorN;  //TODO determinar si no me paso al hacerlo así

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
		
		colorN = colorNave;
		
		ImageIcon EspacioOriginal = new ImageIcon(getClass().getResource("espacio.jpg"));
		Image imagenEspacio = EspacioOriginal.getImage();

		// Escalar al tamaño del panel (ancho x alto)
		Image imagenEspacioEscalada = imagenEspacio.getScaledInstance(450, 300, Image.SCALE_SMOOTH);

		// Crear un ImageIcon con la imagen escalada
		ImageIcon espacioEscalado = new ImageIcon(imagenEspacioEscalada);

		JLabel lblFondo = new JLabel(espacioEscalado);
		lblFondo.setBounds(0, 0, 450, 300);
			
		//contentPane.add(lblFondo);
		contentPane.add(getPanel(mat), BorderLayout.CENTER);
		// Inicializar el controlador y asignarlo
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		contentPane.addKeyListener(getControlador());

		// Agregar este frame como observer del modelo
		Espacio.getEspacio().addObserver(this);	//TODO ¿No debería ser Casilla?	No necesariamente, también va bien así.
		
		vueltas=0;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				vueltas++;
				if(vueltas==4) {vueltas=0;}
				//getControlador();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 50);
	}
	
	private JPanel getPanel(int[][] tabEsp) {//tabEsp:  0=nave 1=disp 2=enem 3=vacío
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(true);
			panel.setLayout(new GridLayout(60, 100, 0, 0));
			JLabel lblNewLabel;
			tablero = new JLabel[60][100];
			for(int f=0;f<60;f++)
			{
				for(int c=0;c<100;c++)
				{
					lblNewLabel = new JLabel("");
					//lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
					//Casilla cas = espacio.getCasilla(f, c);
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
		Object[] res = (Object[]) arg;//arg: acción,estado,posAnt,posNueva,tipo
		int accion = (int) res[0];
		int estado = (int) res[1];
		int[] posAnt = (int[]) res[2];
		int[] posNueva = (int[]) res[3];
		int tipo = (int) res[4];

		if(estado==0) {loose=true;}//perder
		else if(estado==1) //ganar
		{
			if(loose==false)
			{
				win = true;
			}
		}
		else//seguir normal
		{
			int fN=posNueva[0];
			int cN=posNueva[1];
			int fA=posAnt[0];
			int cA=posAnt[1];
			
			if(accion==0)//se quiere mover
			{
				JLabel lblA = tablero[fA][cA];
				JLabel lblN = tablero[fN][cN];
				//borrar la casilla antigua
				lblA.setOpaque(false);
				//dibujar la nueva
				lblN.setOpaque(true);
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
			else if(accion==1)//se borra lo que sea
			{
				JLabel lblA = tablero[fA][cA];
				//borrar la casilla antigua
				lblA.setOpaque(false);
				//TODO notificar que se ha borrado, hay que usar ifs, para cada tipo
			}
			else//if(accion==2) se crea algo nuevo
			{
				JLabel lblN = tablero[fN][cN];
				lblN.setOpaque(true);
				if(tipo==0)//lo que se crea es nave
				{
					lblN.setBackground(Color.RED);
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
		}

	    panel.repaint();//TODO ¿Realmente necesario?
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
			if(vueltas==0 || vueltas==2)
			{
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {model.ListaNaves.getListaNaves().moverNave("left");}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {model.ListaNaves.getListaNaves().moverNave("right");}
				if (e.getKeyCode() == KeyEvent.VK_UP) {model.ListaNaves.getListaNaves().moverNave("up");}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {model.ListaNaves.getListaNaves().moverNave("down");}
			}
			if(vueltas==0) {model.ListaEnem.getListaEnem().moverEnem();}
			model.ListaDisp.getListaDisp().moverDisp();
			if (e.getKeyCode() == KeyEvent.VK_M) {model.ListaDisp.getListaDisp().crearDisp("normal");}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

}
