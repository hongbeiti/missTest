package com.bussiness;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itcast.thread.ThreadPoolTest;

public class ThreadPoolExcutorTest {
	
	private static Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);
	
	@Test
	public void run(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(100), new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						logger.error("reject submit thread to thread pool.");
					}
				});
		
		for (int i = 0; i < 10; i++) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		
		for (int i = 0; i < 5; i++) {
			Future<String> task = executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					return "success";
				}
			});
			
			String res = "";
			try {
				res = task.get();
			} catch (Exception e) {
				logger.error("get result error", e);
			}
			
			System.out.println(res);
		}
	}
}
