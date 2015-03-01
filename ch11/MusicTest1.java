// Example from page 321
package ch11;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MusicTest1 {

	public void play() {
		try {
			Sequencer Sequencer = MidiSystem.getSequencer();
			System.out.println("Successfully got a sequencer");
		} catch (MidiUnavailableException ex) {
			System.out.println("Bummer");
		}
	} // close play

	public static void main(String[] args) {
		MusicTest1 mt = new MusicTest1();
		mt.play();
	} // close main

} // close class