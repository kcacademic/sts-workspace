package com.sapient.learning.events.account.events;

import com.sapient.learning.events.base.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
final public class AccountDebitedEvent extends Event {
	
	private final Double amount;

}
