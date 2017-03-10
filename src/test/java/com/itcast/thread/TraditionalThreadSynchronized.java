package com.itcast.thread;

public class TraditionalThreadSynchronized {
	
	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
	}
	
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("lihuoming");
				}
			}
		}).start();
	}
	
	static class Outputer{
		public void output(String name){
			int len = name.length();
			//保证方法在线程里完全执行，原子执行
			synchronized(Outputer.class){ //this 互斥 字节码同步
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
	}
}
