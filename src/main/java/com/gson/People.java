package com.gson;

import com.google.gson.annotations.SerializedName;

public class People {
	@SerializedName("zhangsan")
	private String name;
	private int age;
	private boolean setName;
	
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
	
	public boolean getSetName() {
		return setName;
	}
	
	public void setSetName(boolean setName) {
		this.setName = setName;
	}
	
	@Override
	public String toString() {
		return "People [name=" + name + ", age=" + age + ", setName=" + setName + "]";
	}
	
}
