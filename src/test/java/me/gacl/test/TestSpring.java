package me.gacl.test;

import me.gacl.service.UserServiceI;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thymeleaf.spring4.resourceresolver.SpringResourceResourceResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

public class TestSpring {

    private ApplicationContext ac;

	@Test
    public void test(){
        ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //从Spring的IOC容器中获取bean对象
        UserServiceI userService = (UserServiceI) ac.getBean("userServiceImpl");
        //执行测试方法
        userService.test();
        
        SpringResourceResourceResolver rs = new  SpringResourceResourceResolver();
        rs.getName();
        
        ThymeleafViewResolver thymeleafViewResolver = null;
        //thymeleafViewResolver.clearCache();
    }
}