// Code magnets exercise

package ch6;

import java.util.ArrayList;

public class ArrayListMagnet {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		a.add(0, "zero");
		a.add(1, "one");
		a.add(2, "two");
		a.add(3, "three");
		printAL(a);

		a.remove(2);
		if (a.contains("three")) {
			a.add("four");
		}
		printAL(a);

		if (a.indexOf("four") != 4) {
			a.add(4, "4.2");
		}
		printAL(a);

		printAL(a);
	}

	public static void printAL(ArrayList<String> list) {
		for (String element : list) {
			System.out.print(element + " ");
		}
		System.out.println(" ");
	}
}