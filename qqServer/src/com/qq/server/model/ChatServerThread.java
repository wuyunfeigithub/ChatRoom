package com.qq.server.model;

import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

import com.qq.common.*;

public class ChatServerThread extends Thread {

	Socket s = null;

	public ChatServerThread(Socket s) {
		this.s = s;
	}
	

	// 通知其他人我上线了
	public void tellOther(String owner) {

		HashMap<String, ChatServerThread> hm = ChatServerThreadManager.hm;
		Iterator<String> it = hm.keySet().iterator();
		
		Message m = new Message();
		m.setMesType(MessageType.message_ret_onLineFriend);
		m.setCon(owner);
		
		while(it.hasNext()){
			
			String getter = it.next().toString();
			m.setGetter(getter);
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(hm.get(getter).s.getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void run() {

		while (true) {
			try {

				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				Message info = (Message) ois.readObject();

				if (info.getMesType().equals(MessageType.message_comm_mes)) {

					System.out.println(info.getSender() + "对"
							+ info.getGetter() + "说：  " + info.getCon());
					// 转发
					Socket ss = ((ChatServerThread) ChatServerThreadManager
							.getHm().get(info.getSender())).s;
					ObjectOutputStream oos = new ObjectOutputStream(
							ss.getOutputStream());
					oos.writeObject(info);
				} 
				else if (info.getMesType().equals(
						MessageType.message_get_onLineFriend)) {

					System.out.println(info.getSender() + "要求在線好友信息");

					Message m2 = new Message();
					m2.setGetter(info.getSender());
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(ChatServerThreadManager.getAllOnLineUserid());

					ObjectOutputStream oos = new ObjectOutputStream(
							s.getOutputStream());
					oos.writeObject(m2);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
