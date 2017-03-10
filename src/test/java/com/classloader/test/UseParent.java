package com.classloader.test;

class Parents{
	static{
		System.out.println("Parent init");
	}
	
	public static int v = 100;
}

class Childs extends Parents{
	static{
		System.out.println("Child init");
	}
}

public class UseParent {
	
	public static void main(String[] args) {
		System.out.println(Childs.v);
	}
}
