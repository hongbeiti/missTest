package com.service;

import java.util.ServiceLoader;

public class Test {
	
	public static void main(String[] args) {
		ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
		for (IService service : serviceLoader) {
			System.out.println(service.getScheme() + "=" + service.sayHello() + "=" + service.getClass().getName());
		}
		System.out.println(Thread.currentThread().getContextClassLoader());
	}
}
