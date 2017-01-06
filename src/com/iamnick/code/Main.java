package com.iamnick.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {


	//static String twitchChannel = "#heiter_bis_pingelig";
	static String twitchChannel = "#1amnick";
	static String account = "1ambot";
	static String OAUTH = "oauth:rornl39klo41tzxxyi9o7rtqiwwk04";



	public static void main(String[] args) throws Exception {


		Socket socket = new Socket("irc.chat.twitch.tv", 6667);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		//request for login
		writer.write("PASS " + OAUTH + "\r\n");
		System.out.println("Submitted Password/OAUTH Token");
		writer.write("NICK " + account + "\r\n");
		System.out.println("NICK " + account);
		writer.flush();

		String line = null;
		while ((line = reader.readLine( )) != null){
			System.out.println(line);
			if(line.contains("376")){//wait for login confirmation
				writer.write("CAP REQ :twitch.tv/membership\r\n"); //gotta see who joins and who become leafs
				System.out.println("CAP REQ :twitch.tv/membership");
				writer.flush();

				break; //continue on 
			}else if(line.contains("Login authentication failed")){
				System.out.println(line);
				Thread.sleep(5000);
				System.exit(-1);
			}
		}

		//I FINALLY get to JOIN!!!!!1!!
		writer.write("JOIN " + twitchChannel + "\r\n");
		writer.flush();

		while ((line = reader.readLine( )) != null){
			System.out.println(line);


			if(line.startsWith("PING ")) {
				writer.write("PONG " + "still alive!" + "\r\n");//it says pong but not in chat
				writer.flush();
				System.out.println("PONG still alive!");// this puts the pong in the log
			}

			if(line.contains(" PRIVMSG ") && !line.contains(account) ){
				//System.out.println("not by me");
				parseMessage(line);
			}
		}


		socket.close();
		

	}

	private static String[] parseMessage(String message){
		String[] tokens = message.split("[:#]");
		String[] temp = tokens[1].split("!");
		tokens[1] = temp[0];
		tokens[2] = "#" + tokens[2];
		
		return tokens;


	}





}
