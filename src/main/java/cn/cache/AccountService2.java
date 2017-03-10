package cn.cache;

import java.util.Optional;

import javax.annotation.Resource;
import javax.resource.spi.IllegalStateException;
import javax.resource.spi.RetryableUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AccountService2 {
	private final Logger logger = LoggerFactory.getLogger(AccountService2.class);
	
	// 使用一个缓存名叫 accountCache
	@Cacheable(value="accountCache")
	public Account getAccountByName(String accountName) throws IllegalStateException{
		//方法内部不考虑缓存逻辑，直接实现业务
		logger.info("real querying account... {}", accountName);
		
		Optional<Account> accountOptional = getFromDB(accountName);
		if(!accountOptional.isPresent()){
			throw new IllegalStateException(String.format("can not find account by account name: [%s]", accountName));
		}
		return accountOptional.get();
	}
	
	private Optional<Account> getFromDB(String accountName){
		logger.info("real query db... {}", accountName);
		//Todo query data from database
		return Optional.ofNullable(new Account(accountName));
	}
	
}
