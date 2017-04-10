package com.qq.client.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import com.qq.client.tools.*;
import com.qq.common.Message;

public class FriendsListView extends JFrame implements ActionListener,
		MouseListener {

	Image icon = null;
	CardLayout card = null;
	// 第一张卡片
	JPanel f_jp1, f_jp2, f_jp3;
	JScrollPane jsp = null;
	JButton jb1 = null;
	JButton jb2 = null;
	JButton jb3 = null;
	// 第二张卡片
	JPanel m_jp1, m_jp2, m_jp3;
	JScrollPane m_jsp = null;
	JButton m_jb1 = null;
	JButton m_jb2 = null;
	JButton m_jb3 = null;
	String owner;
	JLabel[] friend = null;
	JLabel[] mo = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FriendsListView test = new FriendsListView("自己");
	}

	public FriendsListView(String owner) {

		this.owner = owner;
		
		try {
			icon = ImageIO.read(new File("image/qq.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// 处理第一张卡片
		jb1 = new JButton("好友列表");

		f_jp1 = new JPanel(new GridLayout(50, 1, 4, 4));
		friend = new JLabel[50];
		for (int i = 0; i < friend.length; ++i) {
			friend[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"),
					JLabel.LEFT);
			friend[i].setEnabled(false);
			if (this.owner.equals(i + 1 + "")) {
				friend[i].setEnabled(true);
			}
			friend[i].addMouseListener(this);
			f_jp1.add(friend[i]);
		}
		jsp = new JScrollPane(f_jp1);

		f_jp2 = new JPanel(new GridLayout(2, 1));
		jb2 = new JButton("陌生人");
		jb2.addActionListener(this);
		jb3 = new JButton("黑名单");
		f_jp2.add(jb2);
		f_jp2.add(jb3);

		f_jp3 = new JPanel(new BorderLayout());
		f_jp3.add(jb1, "North");
		f_jp3.add(jsp, "Center");
		f_jp3.add(f_jp2, "South");

		// 第二张卡片
		// 处理第一张卡片
		m_jp2 = new JPanel(new GridLayout(2, 1));
		m_jb1 = new JButton("好友列表");
		m_jb1.addActionListener(this);
		m_jb2 = new JButton("陌生人");
		m_jp2.add(m_jb1);
		m_jp2.add(m_jb2);

		m_jp1 = new JPanel(new GridLayout(20, 1, 4, 4));
		mo = new JLabel[20];
		for (int i = 0; i < mo.length; ++i) {
			mo[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"),
					JLabel.LEFT);
			mo[i].addMouseListener(this);
			m_jp1.add(mo[i]);
		}
		m_jsp = new JScrollPane(m_jp1);

		m_jb3 = new JButton("黑名单");

		m_jp3 = new JPanel(new BorderLayout());
		m_jp3.add(m_jp2, "North");
		m_jp3.add(m_jsp, "Center");
		m_jp3.add(m_jb3, "South");

		// JFrame
		card = new CardLayout();
		this.setLayout(card);
		this.add(f_jp3, "0");
		this.add(m_jp3, "1");

		
		this.setIconImage(icon);
		this.setTitle(this.owner);
		this.setSize(200, 500);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.setLocation(width - 250, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	// 更新在线的好友情况
	public void upDateOnlineFriends(Message m) {
		
		String onLineFriend[] = m.getCon().split(" ");

		for (int i = 0; i < onLineFriend.length; i++) {

			this.friend[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == jb2) {
			System.out.println("用户点击了 陌生人");
			card.show(this.getContentPane(), "1");
		} else if (arg0.getSource() == m_jb1) {
			System.out.println("用户点击了好友列表");
			card.show(this.getContentPane(), "0");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			String name = ((JLabel) e.getSource()).getText();
			System.out.println("用户想要和" + name + "聊天");
			Chat c = new Chat(owner, name);
			ManagerQQChat.addChat(owner + "TO" + name, c);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel tmp = (JLabel) e.getSource();
		tmp.setForeground(Color.RED);
		tmp = null;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel tmp = (JLabel) e.getSource();
		tmp.setForeground(Color.BLACK);
		tmp = null;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
