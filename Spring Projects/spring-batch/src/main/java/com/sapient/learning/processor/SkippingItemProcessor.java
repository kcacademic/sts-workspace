package com.sapient.learning.processor;

import com.sapient.learning.exception.MissingUsernameException;
import com.sapient.learning.exception.NegativeAmountException;
import com.sapient.learning.model.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class SkippingItemProcessor implements ItemProcessor<Transaction, Transaction> {

	@Override
	public Transaction process(Transaction transaction) {

		System.out.println("SkippingItemProcessor: " + transaction);

		if (transaction.getUsername() == null || transaction.getUsername().isEmpty()) {
			throw new MissingUsernameException();
		}

		double txAmount = transaction.getAmount();
		if (txAmount < 0) {
			throw new NegativeAmountException(txAmount);
		}

		return transaction;
	}
}
