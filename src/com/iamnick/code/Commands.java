package com.iamnick.code;

import java.io.BufferedWriter;


public class Commands {

	public static void execute(String[] message, BufferedWriter writer) throws Exception {
		if (message[3].startsWith("!changejoin")){
			if(DB.isSubbed(message[1])){//SELECT * from ExternalSub where User like nameQuery
				DB.updateJoin(message[1]);
				Chatter.chat("You are a sub continue!", writer);
			}else{
				Chatter.chat("Sorry this command is for gamewisp subscribers only.", writer);
			}
		}
	}

}
