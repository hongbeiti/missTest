package com.itcast.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		class MyTimer extends TimerTask{

			@Override
			public void run() {
				System.out.println("bombing");
				new Timer().schedule(new MyTimer(), 1000);
			}
		}
		
		new Timer().schedule(new MyTimer(), 1000);
		
		while(true){
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
