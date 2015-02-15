package ch14;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardBuilder {

	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;

	// additional, bonus method not found in any book!

	public static void main(String[] args) {
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.go();
	}

	public void go() {
		cardList = new ArrayList<QuizCard>();

		// build gui
		frame = new JFrame("Quiz Card Builder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // title bar

		// question box
		question = new JTextArea(6, 20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		question.setFont(bigFont);

		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// answer box
		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);

		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton nextButton = new JButton("Next card");
		nextButton.addActionListener(new NextCardListener());
		JLabel qLabel = new JLabel("Question");
		JLabel aLabel = new JLabel("Answer");

		// create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);

		// file menu items
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());

		// file menu
		JMenu fileMenu = new JMenu("File");		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);

		// create and add menu bar to the frame
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(700, 600);
		frame.setVisible(true);
	}

	public class NextCardListener implements ActionListener {
		// add the current card to the list and clear the text areas
		public void actionPerformed(ActionEvent ev) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}
	}

	public class SaveMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// bring up a file dialog box
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);

			// let the user name and save the set
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
	}

	public class NewMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// clear out the card list, and clear out the text areas
			cardList.clear();
			clearCard();
		}
	}

	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}

	private void saveFile(File file) {
		// iterate through the list of cards, and write each one out to a text
		// file in a parseable way (in other words, with clear separations
		// between parts)
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (QuizCard card : cardList) {
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println("couldn't write the cardList out");
			ex.printStackTrace();
		}
	}
}
