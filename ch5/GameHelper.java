package ch5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {
	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize = gridLength * gridLength;
	private int[] grid = new int[gridSize];
	private int comCount = 0;

	public String getUserInput(String prompt) {
		String inputLine = null;
		System.out.println(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if (inputLine.length() == 0)
				return null;
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine;
	}

	public ArrayList<String> placeDotCom(int comSize) {
		ArrayList<String> alphaCells = new ArrayList<>();
		// hold 'f6' type coords
		String[] alphacoords = new String[comSize];
		String temp = null; // temporary String for concat
		int[] coords = new int[comSize]; // current candidate coords
		int attempts = 0; // current attemps counter
		boolean success = false; // flag = found a good location ?
		int location = 0; // current starting position

		comCount++; // nth dot com to place
		int incr = 1; // set horizontal increment
		if ((comCount % 2) == 1)  //if odd dot com (place vertically)
			incr = gridLength;

		while (!success & attempts++ < 200) {
			location = (int) (Math.random() * gridSize);
			int x = 0;
			success = true;
			while (success && x < comSize) {
				if (grid[location] == 0) {
					coords[x++] = location;
					location += incr;
					if (location >= gridSize) {
						success = false;
					}
					if (x > 0 & (location % gridLength == 0)) {
						success = false;
					}
				} else {
					success = false;
				}
			}
		}

		int x = 0;
		int row = 0;
		int column = 0;
		while (x < comSize) {
			grid[coords[x]] = 1;
			row = (int) (coords[x] / gridLength);
			column = coords[x] % gridLength;
			temp = String.valueOf(alphabet.charAt(column));
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
		}
		return alphaCells;
}