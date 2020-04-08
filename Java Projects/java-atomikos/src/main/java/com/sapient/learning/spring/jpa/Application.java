package com.sapient.learning.spring.jpa;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.learning.spring.jpa.inventory.Inventory;
import com.sapient.learning.spring.jpa.inventory.InventoryRepository;
import com.sapient.learning.spring.jpa.order.Order;
import com.sapient.learning.spring.jpa.order.OrderRepository;

public class Application {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Transactional(rollbackFor = Exception.class)
	public void placeOrder(String orderId, String productId, int amount) throws SQLException {

		Inventory inventory = inventoryRepository.findOne(productId);
		inventory.setBalance(inventory.getBalance() - amount);
		inventoryRepository.save(inventory);
		if (orderRepository.exists(orderId)) {
			throw new SQLException("Order already exists.");
		} else {
			Order order = new Order();
			order.setId(orderId);
			order.setProduct(productId);
			order.setBalance(new Long(amount));
			orderRepository.save(order);
		}

	}

}
