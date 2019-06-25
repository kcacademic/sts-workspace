package com.learning.generics;

public class GenericLambda {
	
	public interface Worker {
		<T> T work();
	}
	
	public class MyWorker implements Worker {

		@Override
		public <T> T work() {
			System.out.println("Hello World!");
			return null;
		}
		
	}
	
	public class Climber {
		public void climb(Worker worker) {
			worker.work();
		}
	}
	
	public static void main(String[] args) {
		
		Climber climber = new GenericLambda().new Climber();
		
		//climber.climb(()->{});
		
		climber.climb(new GenericLambda().new MyWorker()::work);
	}

}
