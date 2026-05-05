package viewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Fin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSalir;
	private Image fondo;
	
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
		
		Image bg = new ImageIcon(getClass().getResource("fondo.jpg")).getImage();
		
		fondo = bg;

		// Load overlay images depending on estado:
		// estado == 1 -> youwin.png (single overlay)
		// otherwise -> youlose.png plus dumbPatrick.png shown next to it
		Image overlay = null;
		Image overlay2 = null;
		if (estado == 1) {
			overlay = new ImageIcon(getClass().getResource("youwin.png")).getImage();
		} else {
			overlay = new ImageIcon(getClass().getResource("youlose.png")).getImage();
			// additional image to show when losing
			int opcion = new Random().nextInt(1,10);
			if(opcion > 1)///Mucho más probable
			{
				if (getClass().getResource("dumbPatrick.png") != null) {
					overlay2 = new ImageIcon(getClass().getResource("dumbPatrick.png")).getImage();
				}
			}
			else //if(opcion == 2)
			{
				if (getClass().getResource("patrick-stupid-baba.gif") != null) {
					overlay2 = new ImageIcon(getClass().getResource("patrick-stupid-baba.gif")).getImage();
				}
			}
			
		}

		// Use a custom panel that paints the background and then overlay image(s) centered on top
		contentPane = new BackgroundPanel(fondo, overlay, overlay2);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
        
		setContentPane(contentPane);
		

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

	// Panel that draws a background image stretched to the panel size
	private static class BackgroundPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private final Image bg;
		private final Image overlay1;
		private final Image overlay2;

		BackgroundPanel(Image bg, Image overlay1, Image overlay2) {
			this.bg = bg;
			this.overlay1 = overlay1;
			this.overlay2 = overlay2;
			// let the panel paint its background image
			setOpaque(true);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int w = getWidth();
			int h = getHeight();
			if (bg != null) {
				// draw background stretched to fill
				g.drawImage(bg, 0, 0, w, h, this);
			}

			if (overlay1 != null && overlay2 == null) {
				// single overlay centered (as before)
				int imgW = overlay1.getWidth(this);
				int imgH = overlay1.getHeight(this);
				if (imgW > 0 && imgH > 0) {
					int maxW = w / 2;
					int maxH = h / 2;
					double scale = Math.min((double) maxW / imgW, (double) maxH / imgH);
					if (scale <= 0) scale = 1.0;
					int drawW = (int) (imgW * scale);
					int drawH = (int) (imgH * scale);
					int x = (w - drawW) / 2;
					int y = (h - drawH) / 2;
					g.drawImage(overlay1, x, y, drawW, drawH, this);
				}
			} else if (overlay1 != null && overlay2 != null) {
				// draw dumbPatrick behind youlose, both centered; do not change the relative sizes
				int img1W = overlay1.getWidth(this);
				int img1H = overlay1.getHeight(this);
				int img2W = overlay2.getWidth(this);
				int img2H = overlay2.getHeight(this);
				if (img1W > 0 && img1H > 0 && img2W > 0 && img2H > 0) {
					// Scale down only if an image would be too large for the window
					double scale1 = 1.0;
					double maxWidth1 = w * 0.6;
					double maxHeight1 = h * 0.6;
					if (img1W > maxWidth1 || img1H > maxHeight1) {
						scale1 = Math.min(maxWidth1 / img1W, maxHeight1 / img1H);
					}
					int draw1W = (int) (img1W * scale1);
					int draw1H = (int) (img1H * scale1);

					double scale2 = 1.0;
					double maxWidth2 = w * 0.6;
					double maxHeight2 = h * 0.6;
					if (img2W > maxWidth2 || img2H > maxHeight2) {
						scale2 = Math.min(maxWidth2 / img2W, maxHeight2 / img2H);
					}
					int draw2W = (int) (img2W * scale2);
					int draw2H = (int) (img2H * scale2);

					// Center both images at the panel center; draw overlay2 first (behind) then overlay1 on top
					int centerX = w / 2;
					int centerY = h / 2;
					int x2 = centerX - draw2W / 2;
					int x1 = centerX - draw1W / 2;

					// Move youlose (overlay1) slightly higher and dumbPatrick (overlay2) slightly lower
					int verticalOffset = Math.max(10, draw1H / 6); // offset based on youlose height
					int y1 = centerY - draw1H / 2 - verticalOffset; // youlose higher
					int y2 = centerY - draw2H / 2 + verticalOffset; // dumbPatrick lower

					// Clamp to panel bounds so they remain visible
					if (y1 < 0) y1 = 0;
					if (y2 + draw2H > h) y2 = h - draw2H;

					// If the two images together exceed the horizontal bounds, scale them down proportionally
					int left = Math.min(x1, x2);
					int right = Math.max(x1 + draw1W, x2 + draw2W);
					int totalWidth = right - left;
					int maxAllowedW = (int) (w * 0.95);
					if (totalWidth > maxAllowedW) {
						double down = (double) maxAllowedW / totalWidth;
						draw1W = Math.max(1, (int) (draw1W * down));
						draw1H = Math.max(1, (int) (draw1H * down));
						draw2W = Math.max(1, (int) (draw2W * down));
						draw2H = Math.max(1, (int) (draw2H * down));
						x2 = centerX - draw2W / 2;
						x1 = centerX - draw1W / 2;
						// recompute vertical positions after scaling
						verticalOffset = Math.max(10, draw1H / 6);
						y1 = centerY - draw1H / 2 - verticalOffset;
						y2 = centerY - draw2H / 2 + verticalOffset;
						if (y1 < 0) y1 = 0;
						if (y2 + draw2H > h) y2 = h - draw2H;
					}

					// Draw youlose behind dumbPatrick as requested: draw overlay1 first, then overlay2 on top
					g.drawImage(overlay1, x1, y1 -50, draw1W, draw1H, this);
					g.drawImage(overlay2, x2 -40, y2 +100, draw2W, draw2H, this);
				}
			}
		}
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