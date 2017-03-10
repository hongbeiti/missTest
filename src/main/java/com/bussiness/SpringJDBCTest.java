package com.bussiness;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJDBCTest {

	private static ApplicationContext act;

	public static void main(String[] args) {
		act = new ClassPathXmlApplicationContext("com/bussiness/bean.xml");
		
		UserService userService = (UserService) act.getBean("userService");
		User user = new User();
		user.setName("张三");
		user.setAge(20);
		user.setSex("男");
		//保存一条记录
		//userService.save(user);
		
		List<User> persons = userService.getUsers();
		System.out.println("得到所有User");
		for (User person : persons) {
			System.out.println(person.getId() + " " + person.getName()
					+ " " + person.getAge() + " " + person.getSex());
		}
	}
	
	@Test
	public void save(){
		@SuppressWarnings("rawtypes")
		List mock = mock(List.class);
		when(mock.get(0)).thenReturn(1);
		assertEquals( "预期返回1", 1, mock.get(0));
		
		HttpServletRequest request = mock(HttpServletRequest.class);  
		when(request.getParameter("foo")).thenReturn("boo", "111"); 
		//assertEquals("boo111", request.getParameter("foo"));
		
		//Lambda表达式
		LocalTime localTime = LocalTime.now(); //获取当前时间  
		System.out.println(localTime.toString());//输出当前时间  
	
		Integer i = 8;
		System.out.println(Integer.toUnsignedString(i, 2));
	}

}
