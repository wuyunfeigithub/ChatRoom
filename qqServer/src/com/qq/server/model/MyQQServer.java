package com.qq.server.model;

import java.net.*;
import java.io.*;

import com.qq.common.*;

public class MyQQServer {

	public MyQQServer(){
		try {
			
			System.out.println("我是服务器我在9999端口监听...");
			ServerSocket ss = new ServerSocket(9999);
			
			while (true) {
				Socket s = ss.accept();

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User user = (User)ois.readObject();
                System.out.println("服务器接收到用户id:"+user.getUserId()+"  密码:"+user.getPasswd());
                
                Message m=new Message();
                ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(user.getPasswd().equals("123456"))
				{
					//返回一个成功登陆的信息报					
					m.setMesType("1");
					ChatServerThread chatThread = new ChatServerThread(s);
					
					//把与客户端建立的链接通路放在HashMap中管理
					ChatServerThreadManager.getHm().put(user.getUserId(), chatThread);
					chatThread.start();
					oos.writeObject(m);
					
					//如果有用户上线通知其他用户
					chatThread.tellOther(user.getUserId());
										
				}else{
					m.setMesType("2");
					oos.writeObject(m);
					//关闭Socket
					s.close();
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			
		}
	}
}
