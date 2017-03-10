package cn.cache;

import org.junit.Before;  
import org.junit.Test;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import static org.junit.Assert.*;  

/**
 * http://blog.csdn.net/a494303877/article/details/53780597
 * @author zhihuawang
 *
 */
public class AccountService2Test {  
    private AccountService2 accountService2;  
    private final Logger logger = LoggerFactory.getLogger(AccountService2Test.class);  
	
    @SuppressWarnings("resource")
	@Before  
    public void setUp() throws Exception {  
        accountService2 = new ClassPathXmlApplicationContext("applicationContext1.xml").getBean("accountService2", AccountService2.class);  
    }  
    @Test  
    public void testInject(){  
        assertNotNull(accountService2);  
    }  
    @Test  
    public void testGetAccountByName() throws Exception {  
        logger.info("first query...");  
        accountService2.getAccountByName("accountName");  
        logger.info("second query...");  
        accountService2.getAccountByName("accountName");  
    }  
}  