package com.service;

public class HDFSService implements IService {

	@Override
	public String sayHello() {
		return "Hello HDFSService";
	}

	@Override
	public String getScheme() {
		return "hdfs";
	}

}
