package com.iamnick.code;

import java.io.BufferedWriter;
import java.io.IOException;

public class Commands {

	public static void execute(String[] message, BufferedWriter writer) throws IOException {
		if (message[3].startsWith("!changejoin")){
			if(DB.isSubbed(message[1])){//SELECT * from ExternalSub where User like nameQuery
				
			}else{
				Chatter.chat("Sorry you are not on the sub list", writer);
			}
		}
	}

}
