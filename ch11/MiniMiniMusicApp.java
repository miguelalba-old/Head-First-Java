// Example from page 342

package ch11;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMiniMusicApp {

	public static void main(String[] args) {
		MiniMiniMusicApp mini = new MiniMiniMusicApp();
		mini.play();
	} // close main

	public void play() {
		try {
			// Get a sequencer and open it
			Sequencer player = MidiSystem.getSequencer();
			player.open();

			// Set up sequence
			Sequence seq = new Sequence(Sequence.PPQ, 4);

			// Ask the sequence for a Track
			Track track = seq.createTrack();

			// Put some MidiEvents into the Track
			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, 44, 100);
			MidiEvent noteOn = new MidiEvent(a, 1);
			track.add(noteOn);

			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, 44, 100);
			MidiEvent noteOff = new MidiEvent(b, 16);
			track.add(noteOff);

			// Give the sequence to the sequencer
			player.setSequence(seq);

			// Start() the sequencer
			player.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}