package com.kchandrakant.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.kchandrakant.learning.dao.AccountDao;
import com.kchandrakant.learning.doamin.Account;

@Configuration
public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");

		AccountDao accountDao = context.getBean(AccountDao.class);

		Account account = new Account(null, "BR4303010298012098", 151.00);

		Account createdAccount = (Account) accountDao.createAccount(account).block();

		Account retrievedAccount = (Account) accountDao.findById(createdAccount.getId()).block();

		System.out.println(retrievedAccount);

		context.close();

	}

}
