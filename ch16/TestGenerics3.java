package ch16;

import java.util.*;

public class TestGenerics3 {
	public static void main(String[] args) {
		new TestGenerics3().go();
	}

	public void go() {
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Dog());
		takeAnimals(animals);

		ArrayList<Dog> dogs = new ArrayList<>();
		dogs.add(new Dog());
		dogs.add(new Dog());
		takeAnimals(dogs);
	}

	public void takeAnimals(ArrayList<Animal> animals) {
		for (Animal a : animals) {
			a.eat();
		}
	}
}

abstract class Animal {
	void eat() {
		System.out.println("animal eating");
	}
}

class Dog extends Animal {
	void bark() {}
}

class Cat extends Animal {
	void meow() {}
}