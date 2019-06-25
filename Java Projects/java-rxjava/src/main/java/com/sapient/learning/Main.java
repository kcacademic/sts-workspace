package com.sapient.learning;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
				.filter(new Func1<Integer, Boolean>() {
					@Override
					public Boolean call(Integer integer) {
						return integer % 2 != 0;
					}
				});

		Observer<Integer> observer = new Observer<Integer>() {
			@Override
			public void onCompleted() {
				System.out.println("All data emitted.");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Error received: " + e.getMessage());
			}

			@Override
			public void onNext(Integer s) {
				System.out.println("New data received: " + s);
			}
		};

		Subscription subscription = observable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io())
				.subscribe(observer);
		
		Thread.sleep(1000);
		
		subscription.unsubscribe();
	}

}