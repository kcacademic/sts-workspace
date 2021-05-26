package com.kchandrakant.learning.processor;

import org.springframework.batch.item.ItemProcessor;

import com.kchandrakant.learning.model.Transaction;

public class CustomItemProcessor implements ItemProcessor<Transaction, Transaction> {

	public Transaction process(Transaction item) {
		System.out.println("Processing..." + item);
		return item;
	}
}