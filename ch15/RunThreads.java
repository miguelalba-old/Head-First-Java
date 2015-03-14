package ch15;

public class RunThreads implements Runnable {
	
	public static void main(String[] args) {
		// Make one Runnable instance
		RunThreads runner = new RunThreads();

		// Make the threads
		Thread alpha = new Thread(runner);
		Thread beta = new Thread(runner);

		// Name the threads
		alpha.setName("Alpha thread");
		beta.setName("Beta thread");

		// Start the threads
		alpha.start();
		beta.start();
	}

	public void run() {
		for (int i = 0; i < 25; i++) {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " is running");
		}
	}
}