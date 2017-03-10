package com.itcast.thread;

public class TraditionalThreadComunication {

	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();

		for (int i = 0; i <= 50; i++) {
			business.main(i);
		}
		new Thread().start();
	}
}

class Business {
	private boolean sub = true;
	public synchronized void sub(int i) {
		while (!sub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int j = 0; j <= 10; j++) {
			System.out.println("sub thread sequence of " + i + "," + j);
		}
		sub = false;
		this.notify();
	}

	public synchronized void main(int i) {
		while (sub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 0; j <= 100; j++) {
			System.out.println("main thread sequence of " + i + "," + j);
		}
		sub = true;
		this.notify();
	}
}