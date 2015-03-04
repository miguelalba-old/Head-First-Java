package ch12;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMusicPlayer2 implements ControllerEventListener {
	public static void main(String[] args) {
		MiniMusicPlayer2 mini = new MiniMusicPlayer2();
		mini.go();
	}

	public void go() {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			int[] eventsIWant = {127};
			sequencer.addControllerEventListener(this, eventsIWant);

			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			for (int i = 5; i < 60; i+= 4) {
				track.add(makeEvent(144, 1, i, 100, i));

				track.add(makeEvent(176, 1, 127, 0, i));

				track.add(makeEvent(128, 1, i, 100, i + 2));
			} // end loop

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		} catch (Exception ex) {ex.printStackTrace();}
	} // close

	// every time we get the event, we'll print "la" to the command line
	public void controlChange(ShortMessage event) {
		System.out.println("la");
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {}
		return event;
	} // close class
}