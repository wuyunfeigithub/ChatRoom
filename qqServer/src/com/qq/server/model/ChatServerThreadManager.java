package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

public class ChatServerThreadManager {

	public static HashMap<String, ChatServerThread> hm = new HashMap<String, ChatServerThread>();

	public static HashMap<String, ChatServerThread> getHm() {
		return hm;
	}

	public static void setHm(HashMap<String, ChatServerThread> hm) {
		ChatServerThreadManager.hm = hm;
	}
	
	// ���ص�ǰ���ߵ��˵����
	public static String getAllOnLineUserid() {
		// ʹ�õ��������
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}
}
