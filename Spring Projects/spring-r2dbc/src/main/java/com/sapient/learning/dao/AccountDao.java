package com.sapient.learning.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.sapient.learning.doamin.Account;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AccountDao {

	private ConnectionFactory connectionFactory;

	public AccountDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
		Mono.from(connectionFactory.create()).flatMap(c -> Mono.from(c.createStatement(
				"CREATE TABLE Account (id INT AUTO_INCREMENT  PRIMARY KEY, iban VARCHAR(250) NOT NULL,balance DECIMAL(100,5) NOT NULL)")
				.execute()).doFinally((st) -> close(c))).block();
	}

	public Mono<Account> findById(long id) {

		return Mono.from(connectionFactory.create())
				.flatMap(c -> Mono.from(
						c.createStatement("select id,iban,balance from Account where id = $1").bind("$1", id).execute())
						.doFinally((st) -> close(c)))
				.map(result -> result.map((row, meta) -> new Account(row.get("id", Integer.class),
						row.get("iban", String.class), row.get("balance", BigDecimal.class))))
				.flatMap(p -> Mono.from(p));
	}

	public Flux<Account> findAll() {

		return Mono.from(connectionFactory.create()).flatMap((c) -> Mono
				.from(c.createStatement("select id,iban,balance from Account").execute()).doFinally((st) -> close(c)))
				.flatMapMany(result -> Flux.from(result.map((row, meta) -> {
					Account acc = new Account();
					acc.setId(row.get("id", Integer.class));
					acc.setIban(row.get("iban", String.class));
					acc.setBalance(row.get("balance", BigDecimal.class));
					return acc;
				})));
	}

	public Mono<Account> createAccount(Account account) {

		return Mono.from(connectionFactory.create())
				.flatMap(c -> Mono.from(c.beginTransaction())
						.then(Mono.from(c.createStatement("insert into Account(iban,balance) values($1,$2)")
								.bind("$1", account.getIban()).bind("$2", account.getBalance())
								.returnGeneratedValues("id").execute()))
						.map(result -> result.map((row, meta) -> new Account(row.get("id", Integer.class),
								account.getIban(), account.getBalance())))
						.flatMap(pub -> Mono.from(pub)).delayUntil(r -> c.commitTransaction())
						.doFinally((st) -> c.close()));

	}

	private <T> Mono<T> close(Connection connection) {
		return Mono.from(connection.close()).then(Mono.empty());
	}

}
