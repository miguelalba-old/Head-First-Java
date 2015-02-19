// class from the Pool Puzzle exercise at page 65
package ch3;

class Triangle {
	double area;
	int height;
	int length;
	
	public static void main(String[] args) {
		int x = 0;
		Triangle[] ta = new Triangle[4];
		
		while (x < 4) {
			ta[x] = new Triangle();
			ta[x].height = (x + 1) * 2;
			ta[x].length = x + 4;
			ta[x].setArea();
			System.out.printf("triangle %d, area = %f\n", x, ta[x].area);
			x = x + 1;
		}
		
		int y = x;
		x = 27;
		Triangle t5 = ta[2];
		ta[2].area = 343;
		System.out.printf("y = %d, t5.area = %f", y, t5.area);
	}
	
	void setArea() {
		area = (height * length) / 2;
	}
}
