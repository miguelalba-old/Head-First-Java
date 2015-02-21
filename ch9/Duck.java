// Example from page 242
package ch9;

public class Duck {
	int size;

	public Duck(int size) {
		System.out.println("Quack");
		this.size = size;
		System.out.println("size is " + size);
	}

	public static void main(String[] args) {
		Duck d = new Duck(42);
	}
}