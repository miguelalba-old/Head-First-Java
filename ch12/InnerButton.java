// Be the compiler exercise from page 395

package ch12;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class InnerButton {
	JFrame frame;
	JButton b;

	public static void main(String[] args) {
		InnerButton gui = new InnerButton();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b = new JButton("A");
		b.addActionListener(new BListener());
		frame.getContentPane().add(BorderLayout.SOUTH, b);
		frame.setSize(200, 100);
		frame.setVisible(true);
	}

	class BListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (b.getText().equals("A")) {
				b.setText("B");
			} else {
				b.setText("A");
			}
		}
	}
}