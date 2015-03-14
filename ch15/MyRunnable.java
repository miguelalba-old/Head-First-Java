package ch15;

public class MyRunnable implements Runnable {

	public void run() {
		go();
	}

	public void go() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		doMore();
	}

	public void doMore() {
		System.out.println("top o' the stack");
	}

	public static void main(String[] args) {
		Runnable theJob = new MyRunnable();
		Thread t = new Thread(theJob);
		t.start();
		System.out.println("back in main");
	}
}