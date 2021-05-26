package com.kchandrakant.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kchandrakant.learning.config.domain.Product;
import com.kchandrakant.learning.config.repository.ProductRepository;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");

		ProductRepository repository = context.getBean(ProductRepository.class);

		Product product = new Product();
		product.setId("P0001");
		product.setName("T-Shirt");

		repository.save(product);

		Product retrievedProduct = repository.findOne(product.getId());
		System.out.println(product.getName() + " " + retrievedProduct.getName());

		Thread.sleep(10000);

		context.close();
	}

}
