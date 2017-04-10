package com.qq.server.model;

import java.net.*;
import java.io.*;

import com.qq.common.*;

public class MyQQServer {

	public MyQQServer(){
		try {
			
			System.out.println("���Ƿ���������9999�˿ڼ���...");
			ServerSocket ss = new ServerSocket(9999);
			
			while (true) {
				Socket s = ss.accept();

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User user = (User)ois.readObject();
                System.out.println("���������յ��û�id:"+user.getUserId()+"  ����:"+user.getPasswd());
                
                Message m=new Message();
                ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(user.getPasswd().equals("123456"))
				{
					//����һ���ɹ���½����Ϣ��					
					m.setMesType("1");
					ChatServerThread chatThread = new ChatServerThread(s);
					
					//����ͻ��˽���������ͨ·����HashMap�й���
					ChatServerThreadManager.getHm().put(user.getUserId(), chatThread);
					chatThread.start();
					oos.writeObject(m);
					
					//������û�����֪ͨ�����û�
					chatThread.tellOther(user.getUserId());
										
				}else{
					m.setMesType("2");
					oos.writeObject(m);
					//�ر�Socket
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
