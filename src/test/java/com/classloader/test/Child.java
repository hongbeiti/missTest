package com.classloader.test;

public class Child extends Parent {
	static{
		System.out.println("Child init");
	}
}
