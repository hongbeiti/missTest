package com.itcast.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author zhihuawang
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		//ExecutorService threadPool = Executors.newFixedThreadPool(3);//固定线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();//缓存线程池、Single 单线程池

		for (int i = 1; i <= 10; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 1; j <= 10; j++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " loop of " + j + " for task " + task);
					}
				}
			});
		}
		System.out.println("all of 10 tasks have committed!");
		threadPool.shutdown();//shutdownNow	立即关闭	
		
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("bombing!");
			}
		}, 6, TimeUnit.SECONDS);
	}

}
