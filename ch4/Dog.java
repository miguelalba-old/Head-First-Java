package ch4;

class Dog {

	int size;
	String name;

	void bark() {
		if (size > 60) {
			System.out.println("Woof! Woof!");
		} else if (size > 14) {
			System.out.println("Ruff! Ruff!");
		} else {
			System.out.println("Yip! Yip!");
		}
	}

	public static void main(String[] args) {
		Dog one = new Dog();
		one.size = 70;

		Dog two = new Dog();
		two.size = 8;

		Dog three = new Dog();
		three.size = 35;

		one.bark();
		two.bark();
		three.bark();
	}
}