// class from the Be the compiler exercise at page 21
package ch1;

public class Exercise1a {
	
	public static void main(String[] args) {
		int x = 1;
		while (x < 10) {
			if (x > 3) {
				System.out.println("big x");
			}
			x = x + 1;
		}
	}
}