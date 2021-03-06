package com.ethereal.learning.socket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

@Component
public class SocketHandler extends TextWebSocketHandler {
	
	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		
		for(WebSocketSession webSocketSession : sessions) {
			@SuppressWarnings("rawtypes")
			Map value = new Gson().fromJson(message.getPayload(), Map.class);
			webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//the messages will be broadcasted to all users.
		sessions.add(session);
		System.out.println("Incoming Connection: " + sessions.size());
		for(WebSocketSession webSocketSession : sessions) {
			webSocketSession.sendMessage(new TextMessage("Welcome to the messageboard!"));
		}
	}
}