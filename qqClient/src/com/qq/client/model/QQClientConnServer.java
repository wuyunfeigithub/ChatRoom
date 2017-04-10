package com.qq.client.model;

import java.net.*;
import java.io.*;

import com.qq.common.*;
import com.qq.client.tools.*;

public class QQClientConnServer {

	private  Socket s = null;

	// ����һ������
	public boolean sendLoginInfoToServer(Object o) {
		boolean islogin = false;

		try {
			s = new Socket("127.0.0.1", 9999);

			//��������������֤��½��Ϣ��
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			//���ܷ������ĵ�¼��֤��Ϣ��
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message) ois.readObject();
			
			if (m.getMesType().equals(MessageType.message_succeed)) {
				
				System.out.println("��½��֤�ɹ�");
				//ÿ��½һ��qq����һ���߳�
				ClientConnServerThread ccst = new ClientConnServerThread(s);
				ManegerClientConnServerThread.addClientConnServerThread(((User)o).getUserId(), ccst);
				ccst.start();
				
				islogin = true;
			} else if (m.getMesType().equals(MessageType.message_login_fail)) {
				System.out.println("��½��֤ʧ��");
				islogin = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return islogin;
	}
	
}
