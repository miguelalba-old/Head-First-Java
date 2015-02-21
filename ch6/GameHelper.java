package ch6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;

public class GameHelper {

	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize = gridLength ^ 2;
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
			System.out.println("IOException " + e);
		}
		return inputLine.toLowerCase();
	}

	public ArrayList<String> placeDotCom(int comSize) {
		ArrayList<String> alphaCells = new ArrayList<>();
		// holds 'f6' type coords
		String[] alphacoords = new String[comSize];
		// temporary String for concat
		String temp = null;
		// current candidate coords
		int[] coords = new int[comSize];
		// current attempts counter
		int attempts = 0;
		// flag = found a good location ?
		boolean success = false;
		// current starting location
		int location = 0;

		comCount++; // nth dot com to place
		int incr = 1; // set horizontal increment
		if ((comCount % 2) == 1) { // if odd dot com (place vertically)
			incr = gridLength; // set vertical increment
		}

		// main search loop (32)
		while (!success & attempts++ < 200) {
			// get random starting point
			location = (int) (Math.random() * gridSize);
			// nth position in dotcom to place
			int x = 0;
			// assume success
			success = true;
			// look for adjacent unused spots
			while (success && x < comSize) {
				// if not already used
				if (grid[location] == 0) {
					// save location
					coords[x++] = location;
					// try 'next' adjacent
					location += incr;
					// out of bounds - 'bottom'
					if (location >= gridSize) {
						// failure
						success = false;
					}
					// out of bounds - right edge
					if (x > 0 && (location % gridLength == 0)) {
						// failure
						success = false;
					}
				} else { // found already used location
					success = false; // failure
				}

			}
		}
		
		// turn location into alpha coords
		int x = 0;
		int row = 0;
		int column = 0;
		
		while (x < comSize) {
			// mark master grid pts. as 'used'
			grid[coords[x]] = 1;
			// get row value
			row = (int) (coords[x] / gridLength);
			// get numeric column value
			column = coords[x] % gridLength;
			// convert to alpha
			temp = String.valueOf(alphabet.charAt(column));
			
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
		}
		System.out.println(alphaCells);
		
		return alphaCells;

	}
}