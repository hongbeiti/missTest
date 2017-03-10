package com.okhttp.test;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTest {
	
	public static void main(String[] args) throws Exception{
		OkHttpClient okHttpClient = new OkHttpClient();
	    //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
	    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), "[{\"jobno\":\"123456789\",\"orderNO\":\"\"},{\"jobno\":\"123456789\",\"orderNO\":\"\"}]");
	    //创建一个请求对象
	    Request request = new Request.Builder()
	            .url("http://localhost:8080/HNUST/list")
	            .post(requestBody)
	            .build();
	    
	    int i = 0;
	    //发送请求获取响应
	    try {
	    	for (int j = 0; j < 10; j++) {
	    		 Response response=okHttpClient.newCall(request).execute();
	    		 
	    		 System.out.println(response.code());
	 	        //判断请求是否成功
	 	        if(response.isSuccessful()){
	 	           
	 	        	System.out.println("OK" + i);

	 	        }
	    	}
	 	    } catch (IOException e) {
	 	        e.printStackTrace();
	 	    }
	 	
	    
	 }
	   
	
}