package com.iamnick.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Main {




	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("irc.chat.twitch.tv", 6667);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		Chatter chat = new Chatter(socket, writer, reader);
		

	
	}




}
