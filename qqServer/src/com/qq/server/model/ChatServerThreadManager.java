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
	
	// 返回当前在线的人的情况
	public static String getAllOnLineUserid() {
		// 使用迭代器完成
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}
}
