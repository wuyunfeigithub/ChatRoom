package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.Chat;

public class ManagerQQChat {

	private static HashMap<String, Chat> hm = new HashMap<String, Chat>(); 
	
	public static void addChat(String ownerIdTOchatwithID, Chat c){
		hm.put(ownerIdTOchatwithID, c);
	}
	
	public static Chat getChat(String ownerIdTOchatwithID){
		return (Chat)hm.get(ownerIdTOchatwithID);		
	}
}
