package viewController;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

//import model.Espacio;

import java.awt.BorderLayout;
import javax.swing.JLabel;
//import java.awt.CardLayout;
import java.awt.Color;
//import java.awt.FlowLayout;
//import javax.swing.BoxLayout;
//import java.awt.GridBagLayout;
import java.awt.Image;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.Graphics;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;

public class Menu extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JLabel lblEspacio;
	private Controlador controlador = null;
	private String color = "";
	//Ponemos las imagenes como atributo
	private Image imagenEspacio;
	private Image imagenLogo;
	private JLabel lblColor;
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);	//inicializar a pantalla completa.
		color = "red";
		
		imagenEspacio = new ImageIcon(getClass().getResource("espacio.jpg")).getImage();
		imagenLogo = new ImageIcon(getClass().getResource("space_invaders_logo.png")).getImage(); 
		/*
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		ImageIcon EspacioOriginal = new ImageIcon(getClass().getResource("espacio.jpg"));
		Image imagenEspacioAntigua = EspacioOriginal.getImage();
		Image imagenEspacioEscalada = imagenEspacioAntigua.getScaledInstance(450, 300, Image.SCALE_SMOOTH);
		ImageIcon espacioEscalado = new ImageIcon(imagenEspacioEscalada);
		JLabel lblFondo = new JLabel(espacioEscalado);
		lblFondo.setBounds(0, 0, 450, 300);
		
		ImageIcon LogoOriginal = new ImageIcon(getClass().getResource("SpIn.png"));
		Image imagenLogoAntigua = LogoOriginal.getImage();
		Image imagenLogoEscalada = imagenLogoAntigua.getScaledInstance(250, 100, Image.SCALE_SMOOTH);
		ImageIcon logoEscalado = new ImageIcon(imagenLogoEscalada);
		JLabel lblLogo = new JLabel(logoEscalado);
		lblLogo.setBounds(96, 75, 250, 100);
		*/


		contentPane = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				// Dibuja el fondo adaptándose siempre al ancho y alto actual de la ventana
				if (imagenEspacio != null) {
					g.drawImage(imagenEspacio, 0, 0, getWidth(), getHeight(), this);
				}
				
				// Dibuja el logo escalado al 60% del ancho y lo mantiene centrado
				if (imagenLogo != null) {
					int anchoLogo = (int) (getWidth() * 0.6); 
					int altoLogo = (imagenLogo.getHeight(null) * anchoLogo) / imagenLogo.getWidth(null);
					int posX = (getWidth() - anchoLogo) / 2;
					int posY = (getHeight() - altoLogo) / 3; 
					g.drawImage(imagenLogo, posX, posY, anchoLogo, altoLogo, this);
				}
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	
		model.Espacio.getEspacio().addObserver(this);
		contentPane.requestFocusInWindow();
		contentPane.setFocusable(true);
		contentPane.addKeyListener(getControlador());
		
		/*
		JLabel lblTexto = new JLabel("Presiona <SPACE> para jugar");
		lblTexto.setForeground(Color.WHITE);
		lblTexto.setBounds(150, 220, 250, 15);
		
		contentPane.add(lblTexto);
		contentPane.add(lblLogo);
		contentPane.add(lblFondo);
		*/

	
	    JLabel lblTexto = new JLabel("Presiona <SPACE> para jugar");
	    lblTexto.setForeground(Color.YELLOW); // Cambiamos a amarillo arcade
		lblTexto.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18)); // Letra retro y más grande
		lblTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTexto.setBorder(new EmptyBorder(0, 0, 30, 0)); 
				
				contentPane.add(lblTexto, BorderLayout.SOUTH);
		
		contentPane.add(lblTexto, BorderLayout.SOUTH);
		
		lblColor = new JLabel("Color actual: RED");
		lblColor.setForeground(Color.WHITE);
		lblColor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblColor, BorderLayout.NORTH);
	
	} 

	@Override
	public void update(Observable o, Object arg) {//que pase el color, y el array en forma numérica
		Object[] conv = (Object[]) arg;
		boolean iniciado = (boolean) conv[5];
		if(!iniciado)
		{
			String col = (String) conv[6];
			int[][] mat = (int[][]) conv[7];
			this.setVisible(false);
			Juego juego = new Juego(col,mat);
			juego.setVisible(true);
		}
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
			
			// elegir color
		    if(e.getKeyCode() == KeyEvent.VK_R)
		        color = "red";
		    if(e.getKeyCode() == KeyEvent.VK_G)
		        color = "green";
		    if(e.getKeyCode() == KeyEvent.VK_B)
		        color = "blue";
		    
		    // actualizar color en el menu
		    if(e.getKeyCode() == KeyEvent.VK_R) {
		        color = "red";
		        lblColor.setText("Color actual: RED");
		    }
		    if(e.getKeyCode() == KeyEvent.VK_G) {
		        color = "green";
		        lblColor.setText("Color actual: GREEN");
		    }
		    if(e.getKeyCode() == KeyEvent.VK_B) {
		        color = "blue";
		        lblColor.setText("Color actual: BLUE");
		    }
		    
		    // iniciar juego
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
