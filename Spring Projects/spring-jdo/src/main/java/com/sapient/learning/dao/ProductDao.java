package com.sapient.learning.dao;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import com.sapient.learning.config.Config;
import com.sapient.learning.model.Product;

@Repository
public class ProductDao {

	public Product save(Product product) {
		PersistenceManager pm = Config.getPersistenceManagerFactory().getPersistenceManager();
		return pm.makePersistent(product);
	}

	public void delete(Long productId) {
		PersistenceManager pm = Config.getPersistenceManagerFactory().getPersistenceManager();
		Product product = pm.getObjectById(Product.class, productId);
		pm.deletePersistent(product);
	}

	public Product findById(Long productId) {
		PersistenceManager pm = Config.getPersistenceManagerFactory().getPersistenceManager();
		Product product = pm.getObjectById(Product.class, productId);
		return product;
	}

}