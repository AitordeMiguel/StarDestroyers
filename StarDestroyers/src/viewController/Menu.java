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
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
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
	private JPanel panel;
	private JLabel lblSI;
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
		contentPane.add(getLblEspacio());
		contentPane.add(getPanel_1());
		color = "red";
		model.Espacio.getEspacio().addObserver(this);
	}
	private JLabel getLblEspacio() {
		if (lblEspacio == null) {
			lblEspacio = new JLabel("SPACE INVADERS"
					+ "<Pulse espacio>");
			lblEspacio.setBounds(5, 5, 612, 408);
			//lblEspacio.setIcon(new ImageIcon(this.getClass().getResource("espacio.jpg")));
		}
		return lblEspacio;
	}
	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 612, 408);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblSI(), BorderLayout.CENTER);
			panel.requestFocusInWindow();
			panel.setFocusable(true);
			panel.addKeyListener(getControlador());
		}
		return panel;
	}
	private JLabel getLblSI() {
		if (lblSI == null) {
			lblSI = new JLabel("");
			//lblSI.setIcon(new ImageIcon(this.getClass().getResource("spaceInvaders.png")));
		}
		return lblSI;
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
