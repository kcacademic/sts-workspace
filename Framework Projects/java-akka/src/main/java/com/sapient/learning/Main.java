package com.sapient.learning;

import java.util.Arrays;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.AsPublisher;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

public class Main {

	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("sample-system");
		final Materializer materializer = ActorMaterializer.create(system);

		final Publisher<String> publisher = Source
				.from(Arrays.asList("1", "2", "3", "4", "5"))
				.runWith(Sink.asPublisher(AsPublisher.WITH_FANOUT), materializer);

		Subscriber<String> subscriber = new Subscriber<String>() {
			private org.reactivestreams.Subscription subscription;
			private static final int MAX_NEWS = 3;
			private int received = 0;

			@Override
			public void onSubscribe(org.reactivestreams.Subscription subscription) {
				System.out.printf("new subscription %s\n", subscription);
				this.subscription = subscription;
				subscription.request(1);
			}

			@Override
			public void onNext(String input) {
				System.out.println("Received: " + input);
				received++;
				if (received >= MAX_NEWS) {
					System.out.printf("%d input received (max: %d), cancelling subscription\n", received, MAX_NEWS);
					subscription.cancel();
					return;
				}

				subscription.request(1);
			}

			@Override
			public void onError(Throwable throwable) {
				System.err.printf("error occurred fetching input: %s\n", throwable.getMessage());
				throwable.printStackTrace(System.err);
			}

			@Override
			public void onComplete() {
				System.out.println("fetching input completed");
			}
		};

		publisher.subscribe(subscriber);
	}
}