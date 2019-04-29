package com.io.giulio;

import java.util.Date;

import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.io.giulio.daos.AccountDao;
import com.io.giulio.daos.OperationDao;
import com.io.giulio.daos.UserDao;
import com.io.giulio.model.Account;
import com.io.giulio.model.Operation;
import com.io.giulio.model.User;
import com.io.giulio.utils.EncryptionUtils;

@SpringBootApplication
public class DemoMicroserviziApplication implements CommandLineRunner {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	OperationDao operationDao;
	
	@Autowired
	EncryptionUtils encriptionUtils;
	
	private static final Logger log = LoggerFactory.getLogger(DemoMicroserviziApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoMicroserviziApplication.class, args);
	}

	@Override
	public void run(String...strings) throws Exception {
		
		log.info("hello 1");

		String encryptioned = encriptionUtils.encryption("Mamamia_1");
//		userDao.save(new User("bnfgli84m23b963z","giulio",encryptioned,"admin"));
//		userDao.save(new User("bnfant86m30b963z","antonio",encryptioned,"user") );
//		userDao.save(new User("cccptt84m25b963q","giulio",encryptioned,"user"));
//		accountDao.save(new Account(  "admin","bnfgli84m23b963z", 2.500));
//		accountDao.save(new Account(  "user","bnfant86m30b963z", 2.500));
//		accountDao.save(new Account(  "user","cccptt84m25b963q", 2.500));
//		operationDao.save(new Operation("3452", new Date(), "bonifico", 100.00, "admin", "admin") );
//		operationDao.save(new Operation("3453", new Date(), "bonifico", 150.00, "user", "user") );
//		operationDao.save(new Operation("3454", new Date(), "bonifico", 160.00, "user", "user") );

	}
	
	@Bean
	public BasicTextEncryptor textEncryptor() {
		BasicTextEncryptor textEncryptor= new BasicTextEncryptor();
		textEncryptor.setPassword("my.SecretEncryption-Key{BlaBla1234");
		return textEncryptor;		
	}
}
