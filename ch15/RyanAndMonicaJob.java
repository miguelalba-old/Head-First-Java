package ch15;

class BankAccount {
	// the account starts with a balance of $100
	private int balance = 100;

	public int getBalance() {
		return balance;
	}

	public void withdraw(int amount) {
		balance = balance - amount;
	}
}

public class RyanAndMonicaJob implements Runnable {
	private BankAccount account = new BankAccount();

	public static void main(String[] args) {
		// instantiate the runnable (job)
		RyanAndMonicaJob theJob = new RyanAndMonicaJob();

		// Make 2 threads, giving each thread the same Runnable job. That means
		// both threads will be accessing the one account instance variable in
		// the Runnable class
		Thread one = new Thread(theJob);
		Thread two = new Thread(theJob);
		one.setName("Ryan");
		two.setName("Monica");
		one.start();
		two.start();
	}

	public void run() {
		for (int x = 0; x < 10; x++) {
			makeWithdrawal(10);
			if (account.getBalance() < 0) {
				System.out.println("Overdrawn!");
			}
		}
	}

	private synchronized void makeWithdrawal(int amount) {
		if (account.getBalance() >= amount) {
			System.out.println(Thread.currentThread().getName() +
		 		" is about to withdraw");
			try {
				System.out.println(Thread.currentThread().getName() +
					" is going to sleep");
				Thread.sleep(500);
			} catch (InterruptedException ex) {ex.printStackTrace();}

			System.out.println(Thread.currentThread().getName() + " woke up.");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName() +
				" completes the withdrawal");
		}
		else {
			System.out.println("Sorry, not enough for " +
				Thread.currentThread().getName());
		}
	}
}