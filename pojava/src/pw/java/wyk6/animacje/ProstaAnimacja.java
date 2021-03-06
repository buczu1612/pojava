package pw.java.wyk6.animacje;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class PanelAnimowany extends JPanel implements Runnable {

	// zmienne x,y modyfikowane przez watek
	int x = 0, y = 0;

	public PanelAnimowany() {
		super();
		setPreferredSize(new Dimension(300, 300));
	}

	// metoda run() zmienajaca x,y co 20 ms
	public void run() {

		while (x < 300) {
			x++;
			y++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			repaint(); //repaint domyslnie odpali sie w EDT


		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.fillOval(x, y, 50, 50);

	}

}

public class ProstaAnimacja {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame f = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(350, 350);
				PanelAnimowany panel = new PanelAnimowany();
				f.add(panel);
				f.setVisible(true);

				ExecutorService exec = Executors.newFixedThreadPool(1);
				exec.execute(panel);
				exec.shutdown();

				// new Thread(panel).start();

			}
		});

	}

}
