package viewController;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEspacio;
	private JPanel panel;
	private JLabel lblSI;

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
}
