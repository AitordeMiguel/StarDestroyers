package viewController;

import java.awt.EventQueue;
import java.awt.Image;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego frame = new Juego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Juego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		ImageIcon EspacioOriginal = new ImageIcon(getClass().getResource("espacio.jpg"));
		Image imagenEspacio = EspacioOriginal.getImage();

		// Escalar al tamaño del panel (ancho x alto)
		Image imagenEspacioEscalada = imagenEspacio.getScaledInstance(450, 300, Image.SCALE_SMOOTH);

		// Crear un ImageIcon con la imagen escalada
		ImageIcon espacioEscalado = new ImageIcon(imagenEspacioEscalada);

		JLabel lblFondo = new JLabel(espacioEscalado);
		lblFondo.setBounds(0, 0, 450, 300);
			
		//contentPane.add(lblFondo);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		// Inicializar el controlador y asignarlo
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		contentPane.addKeyListener(getControlador());

		// Agregar este frame como observer del modelo
		Espacio.getEspacio().addObserver(this);		
		
		vueltas=0;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				vueltas++;
				if(vueltas==4) {vueltas=0;}
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 50);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(true);
			panel.setLayout(new GridLayout(60, 100, 0, 0));
			JLabel lblNewLabel;
			tablero = new JLabel[60][100];
			Espacio espacio = Espacio.getEspacio();
			for(int f=0;f<60;f++)
			{
				for(int c=0;c<100;c++)
				{
					lblNewLabel = new JLabel("");
					//lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
					Casilla cas = espacio.getCasilla(f, c);

					if(cas instanceof Enemigo) 
					{
					    lblNewLabel.setOpaque(true);
					    lblNewLabel.setBackground(Color.GRAY);
					}
					else if(cas instanceof Nave) 
					{
					    lblNewLabel.setOpaque(true);
					    Nave n = (Nave) cas;

						if(n.getColor().equals("green")) {
							lblNewLabel.setBackground(Color.GREEN);
						}
						else if(n.getColor().equals("blue")) {
							lblNewLabel.setBackground(Color.BLUE);
						}
						else {
							lblNewLabel.setBackground(Color.RED);
						}
					}
					else if(cas instanceof Disparo) 
					{
					    lblNewLabel.setOpaque(true);
					    lblNewLabel.setBackground(Color.WHITE);
					}
					else {
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
		// Aquí puedes actualizar el tablero si hay cambios en Espacio
		Espacio espacio = Espacio.getEspacio();

	    for(int f=0;f<60;f++)
	    {
	        for(int c=0;c<100;c++)
	        {
	            Casilla cas = espacio.getCasilla(f,c);
	            JLabel lbl = tablero[f][c];

	            if(cas instanceof Enemigo)
	            {
	                lbl.setOpaque(true);
	                lbl.setBackground(Color.DARK_GRAY);
	            }
	            else if(cas instanceof Nave)
	            {
	                lbl.setOpaque(true);

	                Nave n = (Nave) cas;

	                if(n.getColor().equals("green"))
	                    lbl.setBackground(Color.GREEN);
	                else if(n.getColor().equals("blue"))
	                    lbl.setBackground(Color.BLUE);
	                else
	                    lbl.setBackground(Color.RED);
	            }
	            else if(cas instanceof Disparo)
	            {
	                lbl.setOpaque(true);
	                lbl.setBackground(Color.WHITE);
	            }
	            else
	            {
	                lbl.setOpaque(false);
	            }
	        }
	    }

	    panel.repaint();
	}

	// Instancia del controlador
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}

	// Clase interna Controlador
	private class Controlador implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// vacío
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(vueltas==0)
			{
				boolean estado;
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					estado = model.ListaDisp.getListaDisp().moverNave("left");
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					estado = model.ListaDisp.getListaDisp().moverNave("right");
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					estado = model.ListaDisp.getListaDisp().moverNave("up");
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					estado = model.ListaDisp.getListaDisp().moverNave("down");
				}
				model.ListaDisp.getListaDisp().moverEnem();
			}
			model.ListaDisp.getListaDisp().moverDisp();
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				model.ListaDisp.getListaDisp().crearDisp("normal");
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			// vacío
		}
	}
}
