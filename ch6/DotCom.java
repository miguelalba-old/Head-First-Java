package ch6;

import java.util.ArrayList;

public class DotCom {
	private String name;
	private ArrayList<String> locationCells;

	public void setName(String name) {
		this.name = name;
	}

	public void setLocationCells(ArrayList<String> locationCells) {
		this.locationCells = locationCells;
	}

	public String checkYourself(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);

		if (index >= 0) {
			locationCells.remove(index);
			result = locationCells.isEmpty() ? "kill" : "miss";
		}
		return result;
	}
}