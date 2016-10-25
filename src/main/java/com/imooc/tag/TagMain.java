package com.imooc.tag;

import javax.servlet.http.Cookie;
import com.alibaba.fastjson.JSON;


public class TagMain {
	public static void main(String[] args) {
		Order order = JSON.parseObject("{\"createTime\":\"2016-07-05 08:08:08\",\"cityName\":\"杭州\",\"orderItems\":[{\"skuId\":\"1008604\",\"count\":2,\"unitPrice\":8.88},{\"skuId\":\"1008605\",\"count\":1,\"unitPrice\":6.66}]}",
				   Order.class);
	    
		for (OrderItem orderItem : order.getOrderItems()) {
			System.out.println(orderItem);
		}
		
		Cookie cookie = new Cookie("zhangsan", "list");
		cookie.setMaxAge(Integer.MAX_VALUE);
		
		System.out.println(Runtime.getRuntime().maxMemory()/1000/1000);
		 
	}
	
	
}
