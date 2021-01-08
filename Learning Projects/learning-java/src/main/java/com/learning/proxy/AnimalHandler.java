package com.learning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class AnimalHandler implements InvocationHandler {

	private final Animal originalAnimal;

	public AnimalHandler(Animal originalAnimal) {
		this.originalAnimal = originalAnimal;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
		System.out.print("INTERCEPTED ");
		method.invoke(originalAnimal, args); // invoke original method
		return null;
	}

}