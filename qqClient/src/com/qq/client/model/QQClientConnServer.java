package com.qq.client.model;

import java.net.*;
import java.io.*;

import com.qq.common.*;
import com.qq.client.tools.*;

public class QQClientConnServer {

	private  Socket s = null;

	// 发送一次请求
	public boolean sendLoginInfoToServer(Object o) {
		boolean islogin = false;

		try {
			s = new Socket("127.0.0.1", 9999);

			//发给服务器的验证登陆信息包
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			//接受服务器的登录验证信息包
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message) ois.readObject();
			
			if (m.getMesType().equals(MessageType.message_succeed)) {
				
				System.out.println("登陆验证成功");
				//每登陆一个qq启动一个线程
				ClientConnServerThread ccst = new ClientConnServerThread(s);
				ManegerClientConnServerThread.addClientConnServerThread(((User)o).getUserId(), ccst);
				ccst.start();
				
				islogin = true;
			} else if (m.getMesType().equals(MessageType.message_login_fail)) {
				System.out.println("登陆验证失败");
				islogin = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return islogin;
	}
	
}
