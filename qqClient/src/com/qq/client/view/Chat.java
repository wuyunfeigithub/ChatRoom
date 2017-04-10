package com.qq.client.view;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import java.net.*;
import java.io.*;

import com.qq.common.*;
import com.qq.client.model.*;
import com.qq.client.tools.*;

public class Chat extends JFrame implements ActionListener{

	Image icon = null;
	JPanel jp = null;
	JScrollPane jsp = null;
	JTextArea jta = null;
	JTextField jtf = null;
	JButton jb = null;
	String senderId;
	String getterId;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chat test = new Chat("你","**");
	}

	public Chat(String owner,String chatWith){
		
		senderId = chatWith;
		getterId = owner;
		
		try {
			icon = ImageIO.read(new File("image/qq.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jp = new JPanel();
		jtf = new JTextField(15);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jsp, "Center");
		this.add(jp, "South");
		
		this.setIconImage(icon);
		this.setTitle(getterId+"正在和"+senderId+"聊天");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-150, height/2-125);
		this.setSize(300, 250);
		this.setVisible(true);
	}
	
	public void showMessage(Message getinfo){
		jta.append(getinfo.getGetter()+"对"+getinfo.getSender()+"说：     "+getinfo.getCon()+"\r\n");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb){
			Message info = new Message();
			info.setSender(senderId);
			info.setGetter(getterId);
			info.setMesType(3+"");
			info.setSendTime(new java.util.Date().toString());
			info.setCon(jtf.getText());
			
			jta.append(info.getGetter()+"对"+info.getSender()+"说：     "+info.getCon()+"\r\n");
			jtf.setText("");
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManegerClientConnServerThread.getClientConnServerThread(getterId).getSocket().getOutputStream());
				oos.writeObject(info);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
