package com.thread.test;

/**
 * 多线程可见性
 * @author zhihuawang
 *
 */
public class VoliateTest {
	
	public static class MyThread extends Thread{
		
		private volatile boolean stop = false;
		
		public void stopMe(){
			stop = true;
		}
		
		public void run(){
			@SuppressWarnings("unused")
			int i = 0;
			while(!stop){
				i++;
			}
			System.out.println("Stop Thread");
		}
	
	}
	
	public static void main(String[] args) throws InterruptedException{
		MyThread t = new MyThread();
		t.start();
		Thread.sleep(1000);
		t.stopMe();
		Thread.sleep(1000);
	}

}
