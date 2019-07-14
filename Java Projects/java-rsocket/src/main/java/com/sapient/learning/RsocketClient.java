package com.sapient.learning;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

public class RsocketClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(RsocketClient.class);
	
	public static void main(String[] args) throws InterruptedException {

		RsocketClient client = new RsocketClient();
		
		String string = "Kumar Chandrakant";
		LOGGER.info(client.callBlocking(string));
		
		client.getDataStream().index().subscribe(
		        tuple -> {
		        	LOGGER.info(tuple.getT2());
		        },
		        err -> LOGGER.error(err.getMessage()));
		
		client.sendData(2);
		
		DataPublisher dataPublisher = new DataPublisher();
		client.openChannel(dataPublisher).index().subscribe(
		        tuple -> {
		        	LOGGER.info(tuple.getT2());
		        },
		        err -> LOGGER.error(err.getMessage()));
		dataPublisher.publish(DefaultPayload.create("Hello"));
		dataPublisher.publish(DefaultPayload.create("Hello"));
		dataPublisher.publish(DefaultPayload.create("Hello"));


		Thread.sleep(10000);
		
		client.dispose();

	}

	private final RSocket socket;

	public RsocketClient() {
		this.socket = RSocketFactory.connect().transport(TcpClientTransport.create("localhost", 7000)).start().block();
	}

	public String callBlocking(String string) {
		return socket
				.requestResponse(DefaultPayload.create(string))
				.map(Payload::getDataUtf8)
				.block();
	}

	public void sendData(int number) {
		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			dataList.add(new Float(Math.random()).toString());
		}

		Flux.interval(Duration.ofMillis(50))
			.take(dataList.size())
			.map((Long l) -> {return DefaultPayload.create(dataList.get(l.intValue()));})
			.flatMap(socket::fireAndForget)
			.blockLast();
	}
	
    public Flux<String> getDataStream() {
        return socket
          .requestStream(DefaultPayload.create(""))
          .map(Payload::getDataUtf8)
          .onErrorReturn(null);
    }
    
    public Flux<String> openChannel(DataPublisher dataPublisher) {
        return socket.requestChannel(Flux.from(dataPublisher))
          .map(Payload::getDataUtf8)
          .onErrorReturn(null);
    }

	public void dispose() {
		this.socket.dispose();
	}

}