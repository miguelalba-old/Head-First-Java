// Be the compiler example
package ch7;

public class Monster {
	boolean frighten(int x) {
		System.out.println("Arrgh");
		return true;
	}

	public static void main(String[] args) {
		Monster[] ma = new Monster[3];
		ma[0] = new Vampire();
		ma[1] = new Dragon();
		ma[2] = new Monster();
		for (int x = 0; x < 3; x++) {
			ma[x].frighten(x);
		}
	}
}

class Vampire extends Monster {
	boolean frighten(int x) {
		System.out.println("a bite?");
		return true;
	}
}

class Dragon extends Monster {
	boolean frighten(int x) {
		System.out.println("breath fire");
		return true;
	}
}