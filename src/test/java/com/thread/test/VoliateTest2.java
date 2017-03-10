package com.thread.test;

/**
 * 多线程可见性
 * @author zhihuawang
 *
 */
public class VoliateTest2 {
	
	public static class MyThread extends Thread{
		
		private boolean stop = false;
		
		public synchronized void stopMe(){
			stop = true;
		}
		
		public synchronized boolean stopped(){
			return stop;
		}
		
		public void run(){
			@SuppressWarnings("unused")
			int i = 0;
			while(!stopped()){
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
