package ch14;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BeatBoxSaveOnly {

	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;

	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat",
		"Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo",
		"Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
		"Low-mid Tom", "High Agogo", "Open Hi Conga"};
	int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47,
		67, 63};

	public static void main (String[] args) {
		new BeatBoxSaveOnly().buildGUI();
	}

	public void buildGUI() {
		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		// start button
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		// stop button
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		// upTempo button
		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);

		// downTempo button
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);

		// saveIt button
		JButton saveIt = new JButton("Serialize It");
		saveIt.addActionListener(new MySendListener());
		buttonBox.add(saveIt);

		// restore button
		JButton restore = new JButton("Restore");
		restore.addActionListener(new MyReadInListener());
		buttonBox.add(restore);

		// nameBox
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (String instrumentName : instrumentNames)
			nameBox.add(new Label(instrumentName));

		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);

		theFrame.getContentPane().add(background);

		// grid layout
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);

		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		} // end loop

		setUpMidi();

		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
	} // close method

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {e.printStackTrace();}
	}

	public void buildTrackAndStart() {
		// this will hold the instruments for each vertical column,
		// in other words, each tick (may have multiple instruments)
		int[] trackList = null;

		sequence.deleteTrack(track);
		track = sequence.createTrack();

		for (int i = 0; i < 16; i++) {
			trackList = new int[16];

			int key = instruments[i];

			for (int j = 0; j < 16; j++) {
				JCheckBox jc = (JCheckBox) checkboxList.get(j + (16 * i));
				trackList[j] = (jc.isSelected()) ? key : 0;
			} // close inner

			makeTracks(trackList);
		} // close outer

		// so we always go to full 16 beats
		track.add(makeEvent(192, 9, 1, 0, 15));

		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {e.printStackTrace();}
	} // close method

	// listeners

	public class MyStartListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			buildTrackAndStart();
		}
	}

	public class MyStopListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	}

	public class MyUpTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = (float) (sequencer.getTempoFactor() * 1.03);
			sequencer.setTempoFactor(tempoFactor);
		}
	}

	public class MyDownTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = (float) (sequencer.getTempoFactor() * 0.97);
			sequencer.setTempoFactor(tempoFactor);
		}
	}

	public class MySendListener implements ActionListener {
	
		public void actionPerformed(ActionEvent a) {
			// make a boolean array to hold the state of each checkbox
			boolean[] checkboxState = new boolean[256];

			// get the state of each checkbox and add it to the boolean array
			for (int i = 0; i < 256; i++) {
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if (check.isSelected()) {
					checkboxState[i] = true;
				}
			}

			try {
				File f = new File("Checkbox.ser");
				FileOutputStream fileStream = new FileOutputStream(f);
				ObjectOutputStream os = new ObjectOutputStream(fileStream);
				os.writeObject(checkboxState);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} // close method
	} // close inner class

	public class MyReadInListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {
			boolean[] checkboxState = null;
			try {
				FileInputStream fileIn = new FileInputStream(new File("Checkbox.ser"));
				ObjectInputStream is = new ObjectInputStream(fileIn);
				// read the single object in the file and cast it back to a boolean array
				checkboxState = (boolean[]) is.readObject();
			} catch (Exception ex) {ex.printStackTrace();}

			/* restore the state of each of the checkboxes in the ArrayList of
			actual JCheckcBox objects (checkboxList) */
			for (int i = 0; i < 256; i++) {
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				check.setSelected(checkboxState[i]);
			}

			/* stop whatever is currently playing and rebuild the sequence using
			the new state of the checkboxes in the ArrayList */
			sequencer.stop();
			buildTrackAndStart();
		} // close method
	} // close inner class}

	public void makeTracks(int[] list) {
		for (int i = 0; i < 16; i++) {
			int key = list[i];
			if (key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i + 1));
			}
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {}
		return event;
	}
} // close class