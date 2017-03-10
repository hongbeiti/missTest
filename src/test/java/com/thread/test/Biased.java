package com.thread.test;

import java.util.List;
import java.util.Vector;

/**
 * 无参数 2400
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m 430
 * -XX:+UseBiasedLocking -client -Xmx512m -Xms512m 648
 * @author zhihuawang
 *
 */
public class Biased {

	public static List<Integer> numberList = new Vector<Integer>();

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		int count = 0;
		int startnum = 0;
		while (count < 10000000) {
			numberList.add(startnum);
			startnum += 2;
			count++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

}
