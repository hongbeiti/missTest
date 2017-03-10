package cn.com.webxml;

import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import antlr.collections.List;

public class AxisTest {

	public static void main(String[] args) throws Exception{
		test();
		
	        
	}
	
	private static void test() throws Exception{
		//不带？wsdl后缀
		//String endpoint = "http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx"; 
		String endpoint = "http://www.webxml.com.cn/webservices/IpAddressSearchWebService.asmx"; 
        // 创建一个服务(service)调用(call)     
        Service service = new Service();
        //通过service创建call对象 
        Call call = (Call) service.createCall();    
        // 设置service所在URL    
        call.setTargetEndpointAddress(new java.net.URL(endpoint));   
        //qqCheckOnline 是net 那边的方法 "http://WebXml.com.cn/" 这个也要注意Namespace 的地址,不带也会报错
        //call.setOperationName(new QName("http://WebXml.com.cn/","qqCheckOnline")); 
        call.setOperationName(new QName("http://www.WebXml.com.cn/","getCountryCityByIp")); 
        //qqCode也是.NET那边方法的参数名，即qqCheckOnline的参数名
        //call.addParameter(new QName("http://WebXml.com.cn/","qqCode"), 
        call.addParameter(new QName("http://www.WebXml.com.cn/","theIpAddress"), 
               org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        
        //避免Java调用.net的webService产生“服务器未能识别 HTTP 标头 SOAPAction 的值”错误
        call.setUseSOAPAction(true); 
//        QName qn = new QName("http://WebXml.com.cn/", "getCountryCityByIpResponse" );    
//        
//        call.registerTypeMapping(GetCountryCityByIpResponse.class,qn,  
//              new BeanSerializerFactory(GetCountryCityByIpResponse.class, qn),  
//              new BeanDeserializerFactory(GetCountryCityByIpResponse.class, qn));  
//        
//        QName qns = new QName("http://WebXml.com.cn/", "ArrayOfString" );    
//        
//        call.registerTypeMapping(String[].class,qns,  
//              new ArraySerializerFactory(qn, null),  
//              new ArrayDeserializerFactory()); 
//        
        //call.setReturnClass(String.class);
        call.setSOAPActionURI("http://WebXml.com.cn/getCountryCityByIp"); //这个也要注意 就是要加上要调用的方法Add,不然也会报错
        
        // Object 数组封装了参数     
        //String ret = (String) call.invoke(new Object[] {"aaaaa"});    
        Object str = (Object) call.invoke(new Object[] {"36.22.57.66"});    
        System.out.println("--------"+str);
	}

}
