package com.sapient.learning.processor;

import com.sapient.learning.model.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Transaction, Transaction> {

	public Transaction process(Transaction item) {
		System.out.println("Processing..." + item);
		return item;
	}
}