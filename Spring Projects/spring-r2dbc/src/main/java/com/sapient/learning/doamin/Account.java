package com.sapient.learning.doamin;

import java.math.BigDecimal;

public class Account {

	private Integer id;
	private String iban;
	private BigDecimal balance;

	public Account() {
	}

	public Account(Integer id, String iban, BigDecimal balance) {
		this.id = id;
		this.iban = iban;
		this.balance = balance;
	}

	public Account(Integer id, String iban, Double balance) {
		this.id = id;
		this.iban = iban;
		this.balance = new BigDecimal(balance);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", iban=" + iban + ", balance=" + balance + "]";
	}

}
