package com.learning.proxy;

import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) {

		Animal dog = new Dog();
		dog.makeNoise();

		// Build a proxy with our AnimalHandler logic
		Animal proxyDog = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[] { Animal.class },
				new AnimalHandler(dog));

		proxyDog.makeNoise();
		
		System.out.println(dog.getClass());
		System.out.println(proxyDog.getClass());

		System.out.println(dog instanceof Animal); // true
		System.out.println(proxyDog instanceof Animal); // true

		System.out.println(dog instanceof Dog); // true
		System.out.println(proxyDog instanceof Dog); // false

	}

}
