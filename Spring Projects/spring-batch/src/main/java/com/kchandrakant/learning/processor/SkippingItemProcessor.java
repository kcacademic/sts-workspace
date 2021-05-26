package com.kchandrakant.learning.processor;

import org.springframework.batch.item.ItemProcessor;

import com.kchandrakant.learning.exception.MissingUsernameException;
import com.kchandrakant.learning.exception.NegativeAmountException;
import com.kchandrakant.learning.model.Transaction;

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
