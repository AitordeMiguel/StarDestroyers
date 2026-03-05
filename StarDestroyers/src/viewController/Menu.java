package viewController;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Espacio;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Menu extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEspacio;
	private Controlador controlador = null;
	private String color = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		color = "red";
		model.Espacio.getEspacio().addObserver(this);
		contentPane.requestFocusInWindow();
		contentPane.setFocusable(true);
		contentPane.addKeyListener(getControlador());
		
		//JLabel lblFondo = new JLabel(new ImageIcon(getClass().getResource("espacio.jpg")));
		//lblFondo.setBounds(0, 0, 450, 300);
		ImageIcon EspacioOriginal = new ImageIcon(getClass().getResource("espacio.jpg"));
		Image imagenEspacio = EspacioOriginal.getImage();

		// Escalar al tamaño del panel (ancho x alto)
		Image imagenEspacioEscalada = imagenEspacio.getScaledInstance(450, 300, Image.SCALE_SMOOTH);

		// Crear un ImageIcon con la imagen escalada
		ImageIcon espacioEscalado = new ImageIcon(imagenEspacioEscalada);

		JLabel lblFondo = new JLabel(espacioEscalado);
		lblFondo.setBounds(0, 0, 450, 300);
		
		ImageIcon LogoOriginal = new ImageIcon(getClass().getResource("SpIn.png"));
		Image imagenLogo = LogoOriginal.getImage();

		// Escalar al tamaño del panel (ancho x alto)
		Image imagenLogoEscalada = imagenLogo.getScaledInstance(250, 100, Image.SCALE_SMOOTH);

		// Crear un ImageIcon con la imagen escalada
		ImageIcon logoEscalado = new ImageIcon(imagenLogoEscalada);

		JLabel lblLogo = new JLabel(logoEscalado);
		lblLogo.setBounds(96, 75, 250, 100);
		
		JLabel lblTexto = new JLabel("Presiona <SPACE> para jugar");
		lblTexto.setForeground(Color.WHITE);
		lblTexto.setBounds(150, 220, 250, 15);
		
		contentPane.add(lblTexto);
		contentPane.add(lblLogo);
		contentPane.add(lblFondo);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setVisible(false);
		Juego juego = new Juego();
		juego.setVisible(true);
	}
	
	//CONTROLER - INSTANCIA
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}
	
	
	//CONTROLER - LISTENER
	private class Controlador implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			 if (e.getKeyCode() == KeyEvent.VK_SPACE) 
			 {
				 model.ListaDisp.getListaDisp().inicializar(color);
			 }			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		

			
		
		
	}
	
}
