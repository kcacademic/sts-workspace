package com.sapient.learning.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.sapient.learning.WebSocketServer;

//@Configuration
public class EndpointConfig extends SpringConfigurator {
	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
	
	}
	
	@Bean
	public WebSocketServer endpoint()
	{
	      return new WebSocketServer();
	}
}