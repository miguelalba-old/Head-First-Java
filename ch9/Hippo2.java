// Example from page 255

abstract class Animal {
	private String name;

	public String getName() {
		return name;
	}

	public Animal(String name) {
		this.name = name;
	}
}

public class Hippo2 extends Animal {
	public Hippo2(String name) {
		super(name);
	}

	public static void main(String[] args) {
		Hippo2 h = new Hippo2("Buffy");
		System.out.println(h.getName());
	}
}