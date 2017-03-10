package cn.com.webxml;

import java.util.Vector;

public class ThreadUnSave {

	public static Vector<Integer> numberList = new Vector<Integer>();

	public static class AddToList implements Runnable {

		int startnum = 0;

		public AddToList(int startnumber) {
			startnum = startnumber;
		}

		@Override
		public void run() {
			int count = 0;
			while (count < 100000) {
				numberList.add(startnum);
				startnum += 2;
				count++;
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new AddToList(0));
		Thread t2 = new Thread(new AddToList(1));
		t1.start();
		t2.start();
	}
}
