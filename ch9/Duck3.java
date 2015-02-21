// Example from page 244

public class Duck3 {
	int size;

	public Duck3(int size) {
		System.out.println("Quack");
		this.size = size;
		System.out.println("size is " + size);
	}

	public static void main(String[] args) {
		Duck3 d = new Duck3(42);
	}
}