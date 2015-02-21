package ch8;

interface Nose {
	public int iMethod();
}

abstract class Picasso implements Nose {
	public int iMethod() {
		return 7;
	}
}

class Clowns extends Picasso {}

class Acts extends Picasso {
	public int iMethod() {
		return 5;
	}
}

public class Of76 extends Clowns {
	public static void main(String[] args) {
		Nose[] i = new Nose[3];
		i[0] = new Acts();
		i[1] = new Clowns();
		i[2] = new Of76();

		for (Nose x : i) {
			System.out.println(x.iMethod() + " " + x.getClass());
		}
	}
}