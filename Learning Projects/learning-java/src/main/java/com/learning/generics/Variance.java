package com.learning.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Variance {

	public static void main(String[] args) {
		Integer[] myInts = {1, 2, 3, 4, 5};
		Long[] myLongs = {1L, 2L, 3L, 4L, 5L};
		Double[] myDoubles = {1.0, 2.0, 3.0, 4.0, 5.0};
		System.out.println(sum(myInts));
		System.out.println(sum(myLongs));
		System.out.println(sum(myDoubles));

		List<Integer> myInts1 = new ArrayList<Integer>();
		List<Long> myLongs1 = new ArrayList<Long>();
		List<Double> myDoubles1 = new ArrayList<Double>();
		List<Object> myObjs1 = new ArrayList<Object>();
		System.out.println(sumGeneric(myInts1));
		System.out.println(sumGeneric(myLongs1));
		System.out.println(sumGeneric(myDoubles1));

		copy(myInts1, myObjs1);
		copy(myDoubles1, myObjs1);

		// method(new Object());
		method(new Variance().new Sub());
		
		//String str = new Variance().new Sample();
		//System.out.println(str);
		
		String str1 = new Variance().new Sample().transformed().by(Variance::change);
		System.out.println(str1);

	}
	
	public static String change(Sample sample) {
		return sample.toString();
	}

	class Sample implements Transformable {
		@Override
		public Transformer<Sample> transformed() {
			return this::transform; // method reference
		}
		private <R> R transform(Function<? super Sample, ? extends R> f) {
			return f.apply(this);
		}
	}

	@FunctionalInterface
	interface Transformer<T> {
		<R> R by(Function<? super T, ? extends R> f);
	}

	interface Transformable {
		Transformer<?> transformed();
	}

	public static void method(Super str) {

	}

	abstract class Super {
		abstract void doSomething(String parameter);
	}

	class Sub extends Super {
		void doSomething(String parameter) {
		}
		void doSomething(Object parameter) {
		}
	}

	static long sum(Number[] numbers) {
		long summation = 0;
		for (Number number : numbers) {
			summation += number.longValue();
		}
		return summation;
	}

	static long sumGeneric(List<? extends Number> numbers) {
		long summation = 0;
		for (Number number : numbers) {
			summation += number.longValue();
		}
		return summation;
	}

	public static void copy(List<? extends Number> source,
			List<? super Number> destination) {
		for (Number number : source) {
			destination.add(number);
		}
	}

}
