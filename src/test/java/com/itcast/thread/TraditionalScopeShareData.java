package com.itcast.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围共享数据,各自线程的共享数据应该是独立的
 * 单个线程范围内，不管取几次，只能取到一个相同的值
 * 
 * @author zhihuawang
 *
 */
public class TraditionalScopeShareData {

	//private static int data = 0;
	private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + " has put data :" + data);
					threadData.put(Thread.currentThread(), data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	static class A {
		public void get() {
			int data = threadData.get(Thread.currentThread());
			System.out.println("A from " + Thread.currentThread().getName() + " get data :" + data);
		}
	}

	static class B {
		public void get() {
			int data = threadData.get(Thread.currentThread());
			System.out.println("B from " + Thread.currentThread().getName() + " get data :" + data);
		}
	}
}
