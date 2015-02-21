// Pool puzzle example

package ch7;

public class Boat {
	private int length;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void move() {
		System.out.print("drift ");
	}

	public static void main(String[] args) {
		Boat b1 = new Boat();
		Sailboat b2 = new Sailboat();
		Rowboat b3 = new Rowboat();

		b2.setLength(32);
		b1.move();
		b3.move();
		b2.move();
	}
}

class Rowboat extends Boat {
	public void rowTheBoat() {
		System.out.print("stroke natasha");
	}
}

class Sailboat extends Boat {
	public void move() {
		System.out.print("hoist sail ");
	}
}