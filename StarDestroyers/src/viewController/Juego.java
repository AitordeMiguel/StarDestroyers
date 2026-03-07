package viewController;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

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

@SuppressWarnings("deprecation")
public class Juego extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel[][] tablero;
	private Controlador controlador;


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
		contentPane.add(getPanel(), BorderLayout.CENTER);
		
		ImageIcon EspacioOriginal = new ImageIcon(getClass().getResource("espacio.jpg"));
		Image imagenEspacio = EspacioOriginal.getImage();

		// Escalar al tamaño del panel (ancho x alto)
		Image imagenEspacioEscalada = imagenEspacio.getScaledInstance(450, 300, Image.SCALE_SMOOTH);

		// Crear un ImageIcon con la imagen escalada
		ImageIcon espacioEscalado = new ImageIcon(imagenEspacioEscalada);

		JLabel lblFondo = new JLabel(espacioEscalado);
		lblFondo.setBounds(0, 0, 450, 300);
			
		//contentPane.add(lblFondo);
		
		// Inicializar el controlador y asignarlo
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		contentPane.addKeyListener(getControlador());

		// Agregar este frame como observer del modelo
		Espacio.getEspacio().addObserver(this);
		
		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new GridLayout(60, 100, 0, 0));
			JLabel lblNewLabel;
			tablero = new JLabel[60][100];
			Espacio espacio = Espacio.getEspacio();
			for(int f=0;f<60;f++)
			{
				for(int c=0;c<100;c++)
				{
					lblNewLabel = new JLabel("");
					Casilla cas = espacio.getCasilla(f, c);
					if(cas instanceof Enemigo) {
					    lblNewLabel.setOpaque(true);
					    lblNewLabel.setBackground(Color.DARK_GRAY);
					}
					else if(cas instanceof Nave) {
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
					else if(cas instanceof Disparo) {
					    lblNewLabel.setOpaque(true);
					    lblNewLabel.setBackground(Color.WHITE);
					}
					else {
					    lblNewLabel.setOpaque(false);
					}
					//lblNewLabel.setOpaque(true);
					//lblNewLabel.setBackground(Color.BLACK);
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
		repaint();
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
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				// Inicializar disparos
				model.ListaDisp.getListaDisp().inicializar("red"); // color por defecto de la nave
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// vacío
		}
	}
}
