package com.gson;

import java.util.BitSet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {
	public static void main(String[] args) {
		People people = new People();
		people.setAge(20);
		people.setName("People");
		people.setSetName(true);
		//Gson gson = new Gson();
		//System.out.println(gson.toJson(people));	
		
		ExclusionStrategy exclusionStrategy = new  SetterExclusionStrategy();
		Gson gson1 = new GsonBuilder().setExclusionStrategies(exclusionStrategy).create();
		Gson gson2 = new Gson();
		String json1 = gson1.toJson(people);
		String json2 = gson2.toJson(people);
		System.out.println(json1);
		System.out.println(json2);
		
		People p1 = gson1.fromJson(json1, People.class);
		People p2 = gson2.fromJson(json2, People.class);
		System.out.println(p1);
		System.out.println(p2);
		
		BitSet bit = new BitSet();
		bit.set(0, true);
		bit.set(1, true);
		bit.set(2, false);
		System.out.println(bit);
	}
	
	
	private static class SetterExclusionStrategy implements ExclusionStrategy{

		@Override
		public boolean shouldSkipClass(Class<?> arg0) {
			return false;
		}

		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return f.getName().startsWith("set");
		}
		
	}
}
