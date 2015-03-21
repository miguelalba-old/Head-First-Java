package ch16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

class Song implements Comparable<Song> {
	String title;
	String artist;
	String rating;
	String bpm;

	public boolean equals(Object aSong) {
		Song s = (Song) aSong;
		return getTitle().equals(s.getTitle());
	}
	
	public int compareTo(Song song) {
		return title.compareTo(song.getTitle());
	}

	Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	public String getTitle() { return title; }
	public String getArtist() { return artist; }
	public String getRating() { return rating; }
	public String getBpm() { return bpm; }

	public String toString() { return title; }
}

public class Jukebox6 {
	ArrayList<Song> songList = new ArrayList<>();

	public static void main(String[] args) {
		new Jukebox6().go();
	}

	public void go() {
		getSongs();
		System.out.println(songList);
		Collections.sort(songList);
		System.out.println(songList);

		HashSet<Song> songSet = new HashSet<Song>();
		songSet.addAll(songList);
		System.out.println(songSet);
	}

	void getSongs() {
		try {
			File file = new File("SongList.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				addSong(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void addSong(String lineToParse) {
		String[] tokens = lineToParse.split("/");

		Song nextSong = new Song(tokens[0], tokens[1]);
		songList.add(nextSong);
	}
}