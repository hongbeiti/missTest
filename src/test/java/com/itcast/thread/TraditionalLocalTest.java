package com.itcast.thread;

import java.util.Random;		

/**
 * 线程范围共享数据,各自线程的共享数据应该是独立的
 * 单个线程范围内，不管取几次，只能取到一个相同的值
 * ThreadLocal方式
 * 多个线程共享对象和数据的方式
 * 
 * @author zhihuawang
 *
 */
public class TraditionalLocalTest {

	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>();
	//private static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<MyThreadScopeData>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + " has put data :" + data);
					//threadData.put(Thread.currentThread(), data);
					local.set(data);
					//MyThreadScopeData myData = new MyThreadScopeData();
					
					/*myData.setName("name" + data);
					myThreadScopeData.set(myData);
					myData.setAge(data);*/
					MyThreadScopeData.getInstance().setName("name" + data);
					MyThreadScopeData.getInstance().setAge(data);
					new B().get();
					
					
				}
			}).start();
		}
	}

	static class A {
		public void get() {
			//int data = threadData.get(Thread.currentThread());
			int data = local.get();
			//MyThreadScopeData myData = myThreadScopeData.get();//分别取出
			MyThreadScopeData myData = MyThreadScopeData.getInstance();
			System.out.println("A from " + Thread.currentThread().getName() + " get data :" + data + ":" + myData.getAge());
		}
	}

	static class B {
		public void get() {
			//int data = threadData.get(Thread.currentThread());
			int data = local.get();
			MyThreadScopeData myData = MyThreadScopeData.getInstance();
			//MyThreadScopeData myData = myThreadScopeData.get();//分别取出
			System.out.println("B from " + Thread.currentThread().getName() + " get data :" + data + ":" + myData.getAge());
		}
	}
}

class MyThreadScopeData{//优化写法
	
	private MyThreadScopeData(){}
	
	public static /*synchronized*/ MyThreadScopeData getInstance(){
		MyThreadScopeData instance = map.get();
		if(instance == null){
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
	
	//private static MyThreadScopeData instance = null;
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
	
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
