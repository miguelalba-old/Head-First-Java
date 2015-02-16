// class from the Be the compiler exercise at page 21
package ch1;

public class Exercise1b {
	
	public static void main(String[] args) {
		int x = 5;
		while (x > 1) {
			x = x - 1;
			if (x < 3) {
				System.out.println("small x");
			}
		}
	}
}
