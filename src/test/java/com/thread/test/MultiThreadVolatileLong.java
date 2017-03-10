package com.thread.test;

/**
 * 32位long double 原子性测试
 * @author zhihuawang
 *
 */
public class MultiThreadVolatileLong {

	public static long t = 0;

	public static class ChargeT implements Runnable {

		private long to;

		public ChargeT(long to) {
			this.to = to;
		}

		@Override
		public void run() {
			while (true) {
				MultiThreadVolatileLong.t = to;
				Thread.yield();
			}
		}

	}

	public static class ReadT implements Runnable {

		@Override
		public void run() {
			while (true) {
				long tmp = MultiThreadVolatileLong.t;
				if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) 
					System.out.println(tmp);
				Thread.yield();
			}
		}

	}

	public static void main(String[] args) {
		new Thread(new ChargeT(111L)).start();
		new Thread(new ChargeT(-999L)).start();
		new Thread(new ChargeT(333L)).start();
		new Thread(new ChargeT(-444L)).start();
		new Thread(new ReadT()).start();
	}

}
