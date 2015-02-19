package ch5;

public class SimpleDotComGame {
	public static void main(String[] args) {
		int numOfGuesses = 0;
		GameHelper helper = new GameHelper();

		SimpleDotCom dotCom = new SimpleDotCom();
		int randomNum = (int) (Math.random() * 5);

		int[] locations = {randomNum, randomNum + 1, randomNum + 2};
		dotCom.setLocationCells(locations);

		boolean isAlive = true;
		while (isAlive == true) {
			String guess = helper.getUserInput("enter a number");
			String result = dotCom.checkYourself(guess);
			numOfGuesses++;

			if (result.equals("kill")) {
				isAlive = false;
				System.out.println("You took " + numOfGuesses + " guesses");
			}
		}
	}
}