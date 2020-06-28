package com.sapient.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sapient.learning.dao.ProductDao;
import com.sapient.learning.model.Product;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");

		ProductDao dao = context.getBean(ProductDao.class);

		Product product = new Product();
		product.setName("Test Product");
		product.setDescription("Dummy Description");
		product.setCategory("My Category");
		product.setPrice(100.00);

		System.out.println(dao.save(product).getProductId());

		context.close();

	}

}