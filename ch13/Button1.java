// Example from page 408
package ch13;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button1 {
	public static void main(String[] args) {
		Button1 gui = new Button1();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		JButton button = new JButton("click me");
		frame.getContentPane().add(BorderLayout.EAST, button);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}