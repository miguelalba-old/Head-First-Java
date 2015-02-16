// class from page 36
package ch2;

class Dog {
	int size;
	String breed;
	String name;

	void bark() {
		System.out.println("Ruff! Ruff!");
	}
}

public class DogTestDrive {
	public static void main(String[] args) {
		Dog d = new Dog();
		d.size = 40;
		d.bark();
	}
}