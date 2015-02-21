// Example from page 252

class Animal {
	public Animal() {
		System.out.println("Making an Animal");
	}
}

public class Hippo extends Animal {
	public Hippo() {
		System.out.println("Makin a Hippo");
	}

	public static void main(String[] args) {
		System.out.println("Starting...");
		Hippo h = new Hippo();
	}
}