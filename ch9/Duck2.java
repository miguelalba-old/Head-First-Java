// Example from page 243
package ch9;

public class Duck2 {
	int size;

	public Duck2() {
		System.out.println("Quack");
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static void main(String[] args) {
		Duck2 d = new Duck2();
		d.setSize(42);
	}
}