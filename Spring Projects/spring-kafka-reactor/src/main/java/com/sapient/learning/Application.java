package com.sapient.learning;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

public class Application {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");
		KafkaSender<Integer, String> sender = context.getBean(KafkaSender.class);
		KafkaReceiver<Integer, String> receiver = context.getBean(KafkaReceiver.class);

		Flux<SenderRecord<Integer, String, Integer>> outboundFlux = Flux.range(1, 10)
				.map(i -> SenderRecord.create(new ProducerRecord<>("reactive-test", i, "Message_" + i), i));
		sender.send(outboundFlux).doOnError(e -> System.out.println("Send failed: " + e)).doOnNext(
				r -> System.out.printf("Message #%d send response: %s\n", r.correlationMetadata(), r.recordMetadata()))
				.subscribe();

		Flux<ReceiverRecord<Integer, String>> inboundFlux = receiver.receive();
		inboundFlux.subscribe(r -> {
			System.out.printf("Received message: %s\n", r.value());
			r.receiverOffset().acknowledge();
		});

		context.close();
	}

}
