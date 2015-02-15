package ch14;

import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class QuizCardReader {
	private JTextArea display;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private QuizCard currentCard;
	private Iterator<QuizCard> cardIterator;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShownAnswer;

	// additional, bonus method not found in any book!

	public static void main(String[] args) {
		QuizCardReader qReader = new QuizCardReader();
		qReader.go();
	}

	public void go() {
		frame = new JFrame("Quiz Card Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set display
		display = new JTextArea(9, 20);
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		display.setFont(bigFont);
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		display.setEditable(false);

		// set scroll bars
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// next button
		nextButton = new JButton("Show Question");
		nextButton.addActionListener(new NextCardListener());

		// create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);

		// file menu items
		JMenuItem loadMenuItem = new JMenuItem("Load card set");
		loadMenuItem.addActionListener(new OpenMenuListener());

		// file menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(loadMenuItem);

		// menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	} // close go

	public class NextCardListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (isShownAnswer) {
				// show the answer because they've seen the question
				display.setText("That was last card");
				nextButton.setText("Next Card");
				isShownAnswer = false;	
			} else {
				// show the next question
				if (cardIterator.hasNext()) {
					showNextCard();
				} else {
					// there are no more cards!
					display.setText("That was last card");
					nextButton.disable();
				}
			} // close if
		} // close method
	} // close inner class

	public class OpenMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
		}
	}

	private void loadFile(File file) {
		cardList = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				makeCard(line);
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("couldn't read the card file");
			ex.printStackTrace();
		}

		// now time to start
		cardIterator = cardList.iterator();
		showNextCard();
	}

	private void makeCard(String lineToParse) {
		StringTokenizer parser = new StringTokenizer(lineToParse, "/");
		if (parser.hasMoreTokens()) {
			QuizCard card = new QuizCard(parser.nextToken(), parser.nextToken());
			cardList.add(card);
		}
	}

	private void showNextCard() {
		currentCard = cardIterator.next();
		display.setText(currentCard.getQuestion());
		nextButton.setText("Show Answer");
		isShownAnswer = true;
	}
} // close class