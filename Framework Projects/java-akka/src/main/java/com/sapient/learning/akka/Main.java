package com.sapient.learning.akka;

import static akka.pattern.PatternsCS.ask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ActorSystem actorSystem = ActorSystem.create("mySystem");

		ActorRef helloActorRef = actorSystem.actorOf(HelloActor.props(), "helloActor");
		helloActorRef.tell(new HelloMessage("Hello, Akka!"), ActorRef.noSender());

		ActorRef wordCounterActorRef = actorSystem.actorOf(WordCounterActor.props(), "wordCounterActor");
		CompletableFuture<Object> future = ask(wordCounterActorRef, new WordCount("This is a dumnmy line."), 1000)
				.toCompletableFuture();
		System.out.println(future.get());

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		actorSystem.stop(helloActorRef);
		actorSystem.stop(wordCounterActorRef);
		actorSystem.terminate();
	}

}
