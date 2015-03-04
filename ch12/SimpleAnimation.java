package ch12;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleAnimation {

	int x = 70;
	int y = 70;

	public static void main(String[] args) {
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyDrawPanel drawPanel = new MyDrawPanel();

		frame.getContentPane().add(drawPanel);
		frame.setSize(300, 300);
		frame.setVisible(true);

		// Repeat this 130 times
		for (int i = 0; i <130; i++) {
			x++; // increment the x and y coordinates
			y++;

			drawPanel.repaint();

			try {
				Thread.sleep(50);
			} catch (Exception ex) { }
		}
	} // close go() method

	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);
		} // close inner class
	} // close outer class
}