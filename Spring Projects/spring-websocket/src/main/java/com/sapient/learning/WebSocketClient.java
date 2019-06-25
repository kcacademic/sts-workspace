package com.sapient.learning;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketClient {
	private static Object waitLock = new Object();

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received msg: " + message);
	}

	private static void wait4TerminateSignal() {
		synchronized (waitLock) {
			try {
				waitLock.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String[] args) {
		WebSocketContainer container = null;
		Session session = null;
		WebSocketClient wsc = null;
		try {
			container = ContainerProvider.getWebSocketContainer();
			wsc = new WebSocketClient();
			session = container.connectToServer(wsc, URI.create("ws://localhost:8080/name"));
			//session = container.connectToServer(wsc, URI.create("ws://localhost:8080/ratesrv"));
			session.getBasicRemote().sendText("{\"name\":\"Kumar Chandrakant\"}", true);
			wait4TerminateSignal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}