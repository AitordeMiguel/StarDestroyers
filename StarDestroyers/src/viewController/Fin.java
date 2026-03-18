package viewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Fin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMensaje;
	private JLabel lblSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fin frame = new Fin(0); // prueba: 0 perder, 1 ganar
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
	public Fin(int estado) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());

		setContentPane(contentPane);		
		
		// Mensaje principal
		lblMensaje = new JLabel("", SwingConstants.CENTER);
		lblMensaje.setFont(new Font("Monospaced", Font.BOLD, 60));

		if(estado == 1) {
			lblMensaje.setText("¡HAS GANADO!");
			lblMensaje.setForeground(Color.GREEN);
		}
		else {
			lblMensaje.setText("GAME OVER");
			lblMensaje.setForeground(Color.RED);
		}

		contentPane.add(lblMensaje, BorderLayout.CENTER);

		// Texto inferior
		lblSalir = new JLabel("Pulsa ESC para salir", SwingConstants.CENTER);
		lblSalir.setFont(new Font("Monospaced", Font.BOLD, 25));
		lblSalir.setForeground(Color.WHITE);

		contentPane.add(lblSalir, BorderLayout.SOUTH);

		// Control de teclado
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		contentPane.addKeyListener(new Controlador());
	}

	// Controlador del teclado
	private class Controlador implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
	}
}