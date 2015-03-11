package ch15;

public class MyRunnable implements Runnable {

	public void run() {
		go();
	}

	public void go() {
		doMore();
	}

	public void doMore() {
		System.out.println("top o' the stack");
	}

	public static void main(String[] args) {
		Thread myThread = new Thread(new MyRunnable());
		myThread.start();
		System.out.println("back in main");
	}
}