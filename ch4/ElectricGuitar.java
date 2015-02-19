package ch4;

class ElectricGuitar {
	String brand;
	int numOfPicks;
	boolean rockStartUsesIt;

	String getBrand() {
		return brand;
	}

	void setBrand(String brand) {
		this.brand = brand;
	}

	int getNumOfPicks() {
		return numOfPicks;
	}

	void setNumOfPicks(int numOfPicks) {
		this.numOfPicks = numOfPicks;
	}

	boolean getRockStarUsesIt() {
		return rockStartUsesIt;
	}

	void setRockStarUsesIt(boolean rockStartUsesIt) {
		this.rockStartUsesIt = rockStartUsesIt;
	}
}