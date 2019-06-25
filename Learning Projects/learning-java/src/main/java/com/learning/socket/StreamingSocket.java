package com.learning.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamingSocket {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException, InterruptedException {

		ServerSocket server = new ServerSocket(6066);
		System.out.println("Waiting for client");
		Socket channel = server.accept();
		System.out.println("Client arrived!");
		PrintWriter out = new PrintWriter(channel.getOutputStream());
		InputStreamReader reader = new InputStreamReader(channel.getInputStream());
		BufferedReader in = new BufferedReader(reader);
		
		while(true) {
			System.out.println("Comminicating now!");
			out.println("Hey! I heard you over this socket!");
			Thread.sleep(10000);
		}

	}

}
