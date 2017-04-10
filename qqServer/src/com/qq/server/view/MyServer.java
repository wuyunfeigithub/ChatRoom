package com.qq.server.view;


import javax.swing.*;

import java.awt.Toolkit;
import java.awt.event.*;

import com.qq.server.model.*;

public class MyServer extends JFrame implements ActionListener{

	JButton start, end;
	JPanel jp = null; 
	MyQQServer qqServer = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer my = new MyServer();
	}
	
	public MyServer(){
		jp = new JPanel();
		start = new JButton("启动服务器");
		start.addActionListener(this);
		end = new JButton("关闭服务器");
		end.addActionListener(this);
		
		jp.add(start);
		jp.add(end);
		
		this.add(jp, "North");
		
		this.setSize(400, 300);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-200);
		this.setTitle("qq服务器");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==start){
			System.out.println("启动服务器");
			qqServer = new MyQQServer();
		}
		else if(arg0.getSource()==end){
			System.out.println("关闭服务器");
		}
	}

}
