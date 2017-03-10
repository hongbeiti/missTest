package cn.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.fabric.xmlrpc.Client;

public class AccountServiceTest {
	private AccountServiceI accountServiceI;
	private final Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);
	@Before
	public void setUp() throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext1.xml");
		accountServiceI = context.getBean("accountServiceI", AccountServiceI.class);
	}
	@Test
	public void testInject(){
		Assert.assertNotNull(accountServiceI);
	}
	@Test
	public void testGetAccountByName() throws Exception{
		accountServiceI.getAccountByName("accountName");
		accountServiceI.getAccountByName("accountName");
		accountServiceI.reload();
		logger.info("after reload ....");
		accountServiceI.getAccountByName("accountName");
		accountServiceI.getAccountByName("accountName");
	}
	
}
