package com.qq.client.tools;

import java.net.*;
import java.io.*;

import com.qq.client.view.Chat;
import com.qq.common.*;

public class ClientConnServerThread extends Thread {

	private Socket s = null;

	public ClientConnServerThread(Socket s) {
		this.s = s;
	}

	public Socket getSocket() {
		return s;
	}

	public void run() {
		while (true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				Message m = (Message) ois.readObject();

				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					System.out.println(m.getGetter() + "对" + m.getSender()
							+ "说：   " + m.getCon());
					Chat c = ManagerQQChat.getChat(m.getSender() + "TO"
							+ m.getGetter());
					c.showMessage(m);
				} 
				else if (m.getMesType().equals(
						MessageType.message_ret_onLineFriend)) {

					System.out.println("在线好友：   " + m.getCon());
					System.out.println(m.getGetter());
					
					if (ManagerFriendList.getFriendsList(m.getGetter()) != null) {
						System.out.println("准备更新好友列表...");
						ManagerFriendList.getFriendsList(m.getGetter())
								.upDateOnlineFriends(m);
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
