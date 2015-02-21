// Sharpen your pencil exercise

class Duck4 {
	int pounds = 6;
	float floatability = 2.1F;
	String name = "Generic";
	long[] feathers = {1, 2, 3, 4, 5, 6, 7};
	boolean canFly = true;
	int maxSpeed = 25;

	public Duck4() {
		System.out.println("type 1 duck");
	}

	public Duck4(boolean fly) {
		canFly = fly;
		System.out.println("type 2 duck");
	}

	public Duck4(String n, long[] f) {
		name = n;
		feathers = f;
		System.out.println("type 3 duck");
	}

	public Duck4(int w, float f) {
		pounds = w;
		floatability = f;
		System.out.println("type 4 duck");
	}

	public Duck4(float density, int max) {
		floatability = density;
		maxSpeed = max;
		System.out.println("type 5 duck");
	}

	public static void main(String[] args) {
		int weight = 8;
		float density = 2.3F;
		String name = "Donald";
		long[] feathers = {1, 2, 3, 4, 5, 6};
		boolean canFly = true;
		int airspeed = 22;

		Duck4[] d = new Duck4[7];
		d[0] = new Duck4();
		d[1] = new Duck4(density, weight);
		d[2] = new Duck4(name, feathers);
		d[3] = new Duck4(canFly);
		d[4] = new Duck4(3.3F, airspeed);
		d[5] = new Duck4(false);
		d[6] = new Duck4(airspeed, density);
	}
}