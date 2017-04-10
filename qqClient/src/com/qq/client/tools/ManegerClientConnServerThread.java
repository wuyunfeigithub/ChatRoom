package com.qq.client.tools;

import java.util.HashMap;

public class ManegerClientConnServerThread {

	private static HashMap<String, ClientConnServerThread> hm = new HashMap<String, ClientConnServerThread>();

	public static void addClientConnServerThread(String ownerId,ClientConnServerThread ccst){
		hm.put(ownerId, ccst);
	}
	
	public static ClientConnServerThread getClientConnServerThread(String ownerId){
		return (ClientConnServerThread)hm.get(ownerId);		
	}

}
