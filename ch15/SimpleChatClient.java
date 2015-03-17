package ch15;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;



public class SimpleChatClient {

	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;

	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.go();
	}

	public void go() {
		incoming = new JTextArea(15, 50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(true);
		
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		outgoing = new JTextField(20);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		setUpNetworking();

		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();

		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400, 500);
		frame.setVisible(true);
	} // close go

	private void setUpNetworking() {
		try {
			sock = new Socket("127.0.0.1", 5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // close setUpNetworking

	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println(outgoing.getText());
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	} // close inner class

	public class IncomingReader implements Runnable {
		public void run() {
			String message;

			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("read " + message);
					incoming.append(message + "\n");
				} // close while
			} catch (Exception ex) {ex.printStackTrace();}
		} // close run
	} // close inner class
} // close outer class