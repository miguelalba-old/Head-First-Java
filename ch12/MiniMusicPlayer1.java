package ch12;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMusicPlayer1 {
	public static void main(String[] args) {
		try {
			// make and open a sequencer
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			// make a sequence and a track
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			// make a bunch of events to make the notes keep going up
			// (from piano note 5 to piano note 61)
			for (int i = 5; i < 61; i+=4) {
				track.add(makeEvent(144, 1, i, 100, i));
				track.add(makeEvent(128, 1, i, 100, i + 2));
			} // end loop

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		} catch (Exception ex) {ex.printStackTrace();}
	} // close main

	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
		} catch (Exception e) { }
		return event;
	} // close class
}