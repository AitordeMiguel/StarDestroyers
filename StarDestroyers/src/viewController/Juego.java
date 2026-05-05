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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Espacio;
import model.ListaNaves;

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
	private JPanel panelInfo;
	private JLabel lblInfo1;
	private JLabel lblInfo2;
	private JLabel lblInfo3;
	private JLabel lblInfo4;

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
		panelInfo = new JPanel();
		panelInfo.setLayout(new java.awt.GridLayout(1, 4)); // 4 columnas iguales
		panelInfo.setBackground(Color.BLACK); // Fondo negro para que destaque
		
		// 2. Crear los 4 Labels (puedes cambiarles el texto inicial aquí)
		lblInfo1 = new JLabel("PUNTOS: 0", SwingConstants.CENTER);
		lblInfo1.setForeground(Color.WHITE);
		lblInfo1.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
		
		lblInfo2 = new JLabel("ARMA: NORMAL", SwingConstants.CENTER);
		lblInfo2.setForeground(Color.WHITE);
		lblInfo2.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
		
		if(colorNave=="blue") 
		{
			lblInfo3 = new JLabel("FLECHAS: 0", SwingConstants.CENTER);
			lblInfo3.setForeground(Color.RED);
			lblInfo3.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
			
			lblInfo4 = new JLabel("ROMBOS: 20", SwingConstants.CENTER);
			lblInfo4.setForeground(Color.WHITE);
			lblInfo4.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
		}
		else if(colorNave=="green")
		{
			lblInfo3 = new JLabel("FLECHAS: 30", SwingConstants.CENTER);
			lblInfo3.setForeground(Color.WHITE);
			lblInfo3.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
			
			lblInfo4 = new JLabel("ROMBOS: 0", SwingConstants.CENTER);
			lblInfo4.setForeground(Color.RED);
			lblInfo4.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
		}
		else if(colorNave =="red")
		{
			lblInfo3 = new JLabel("FLECHAS: 30", SwingConstants.CENTER);
			lblInfo3.setForeground(Color.WHITE);
			lblInfo3.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
			
			lblInfo4 = new JLabel("ROMBOS: 20", SwingConstants.CENTER);
			lblInfo4.setForeground(Color.WHITE);
			lblInfo4.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 18));
		}
		
		// 3. Añadir los labels al panelInfo
		panelInfo.add(lblInfo1);
		panelInfo.add(lblInfo2);
		panelInfo.add(lblInfo3);
		panelInfo.add(lblInfo4);
		
		// 4. Añadir el panelInfo a la parte inferior de la pantalla (SOUTH)
		lblFondo.add(panelInfo, BorderLayout.SOUTH);
		// --- HASTA AQUÍ ---

		// Inicializar el controlador y asignarlo
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(getControlador());
		// Inicializar el controlador y asignarlo
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(getControlador());

		// Agregar este frame como observer del modelo
		Espacio.getEspacio().addObserver(this);	
		ListaNaves.getListaNaves().addObserver(this);
	}
	
	private JPanel getPanel(int[][] tabEsp) {//tabEsp:  0=nave 1=disp 2=enem 3=vacío
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new GridLayout(60, 100, 0, 0));    //TODO límites del tablero
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
					int[] coor = (int[]) res[6];
					int f = coor[0];
					int c = coor[1];
					JLabel lbl = tablero[f][c];
					if((int) res[7]==0)//Si la acción es borrar
					{
						lbl.setOpaque(false);
						lbl.repaint();
					}
					else //Si es pintar
					{
						int tipo = (int) res[8];
						if(tipo==0)//Si hay que pintar nave
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
						else if(tipo==1)//Si es disp
						{
							lbl.setOpaque(true);
							lbl.setBackground(Color.YELLOW);
							lbl.repaint();
						}
						else if(tipo==2)//Si es enem, aunque podría ser solo un else
						{
							lbl.setOpaque(true);
							lbl.setBackground(Color.GRAY);
							lbl.repaint();
						}
					}
					
				}
			}
		}
		else if((int) res[0]==4)//Se dirige concretamente al panelInfo 
		{
			int info = (int) res[9];
			int valor = (int) res[10];
			if(info == 0)//Cambiar puntos
			{
				lblInfo1.setText("PUNTOS: "+valor);
			}
			else if(info == 1)//Cambiar cant disp flecha
			{
				lblInfo3.setText("FLECHAS: "+ valor);
				if(valor == 0)
				{
					lblInfo3.setForeground(Color.RED);
				}
			}
			else if(info == 2)//Cambiar cant disp rombo
			{
				lblInfo4.setText("ROMBOS: "+valor);
				if(valor == 0)
				{
					lblInfo4.setForeground(Color.RED);
				}
			}
			else if(info == 3)//Cambiar a normal
			{
				lblInfo2.setText("ARMA: NORMAL");
			}
			else if(info == 4)//Cambiar a flecha
			{
				lblInfo2.setText("ARMA: FLECHA");
			}
			else if(info == 5)//Cambiar a rombo
			{
				lblInfo2.setText("ARMA: ROMBO");
			}
		}
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
			
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {model.ListaNaves.getListaNaves().crearDisp();}
			
			if (e.getKeyCode() == KeyEvent.VK_C) {model.ListaNaves.getListaNaves().cambiarDisp(3);}//Cambiar a Rombo
			if (e.getKeyCode() == KeyEvent.VK_X) {model.ListaNaves.getListaNaves().cambiarDisp(2);}//Cambiar a Flecha
			if (e.getKeyCode() == KeyEvent.VK_Z) {model.ListaNaves.getListaNaves().cambiarDisp(1);}//Cambiar a Normal
			
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

}
