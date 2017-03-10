package com.itcast.thread;

/**
 * 线程运行的2种方式
 * @author zhihuawang
 *
 */
public class TraditionalThread {

	public static void main(String[] args) {
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1:" + Thread.currentThread().getName());
					System.out.println("1:" + this.getName());//有Thread对象时，才能用
				}
				//super.run();
			}			
		};
		thread.start();
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("2:" + Thread.currentThread().getName());
					//System.out.println("2:" + this.getName());
				}
			}
		});//Runable对象传递给Thread方法
		thread2.start();	
		
		new Thread(new Runnable() {// 父类实现
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread:" + Thread.currentThread().getName());
				}
			}
		}){
			public void run() { //子类实现
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("3:" + Thread.currentThread().getName());
				}
			}
		}.start();
	}

}
