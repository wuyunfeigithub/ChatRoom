/**
 * ���칤�ߵ�½����
 */
package com.qq.client.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.qq.client.model.QQClientUser;
import com.qq.client.tools.ManagerFriendList;
import com.qq.client.tools.ManegerClientConnServerThread;
import com.qq.common.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Login extends JFrame implements ActionListener {

	Image icon = null;
	// ����
	JLabel north_Label = null;
	// �в�
	JTabbedPane jtp = null;
	JPanel center_Panel1, center_Panel2, center_Panel3;
	JLabel center_label1, center_label2, center_label3, center_label4;
	JTextField user = null;
	JPasswordField password = null;
	JButton center_bt = null;
	JCheckBox center_jcb1, center_jcb2;
	// �ϲ�
	JPanel south_Panel = null;
	JButton south_bt1, south_bt2, south_bt3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login text = new Login();
	}

	public Login() {

		try {
			icon = ImageIO.read(new File("image/qq.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// ��ʼ������
		north_Label = new JLabel(new ImageIcon("image/logo.gif"));

		// ��ʼ���в�
		jtp = new JTabbedPane();

		center_Panel1 = new JPanel(new GridLayout(3, 3));

		center_label1 = new JLabel("QQ����", JLabel.CENTER);
		center_label2 = new JLabel("QQ����", JLabel.CENTER);
		center_label3 = new JLabel("��������", JLabel.CENTER);
		center_label3.setForeground(Color.BLUE);
		center_label4 = new JLabel("�������뱣��", JLabel.CENTER);
		center_label4.setForeground(Color.blue);
		user = new JTextField();
		password = new JPasswordField();
		center_bt = new JButton(new ImageIcon("image/clear.gif"));
		center_bt.addActionListener(this);
		center_jcb1 = new JCheckBox("�����½");
		center_jcb2 = new JCheckBox("��ס����");

		center_Panel1.add(center_label1);
		center_Panel1.add(user);
		center_Panel1.add(center_bt);
		center_Panel1.add(center_label2);
		center_Panel1.add(password);
		center_Panel1.add(center_label3);
		center_Panel1.add(center_jcb1);
		center_Panel1.add(center_jcb2);
		center_Panel1.add(center_label4);

		center_Panel2 = new JPanel();
		center_Panel3 = new JPanel();

		jtp.add("QQ����", center_Panel1);
		jtp.add("�ֻ�����", center_Panel2);
		jtp.add("�����ʼ�", center_Panel3);

		// ��ʼ���ϲ�
		south_Panel = new JPanel();
		south_bt1 = new JButton(new ImageIcon("image/login.gif"));
		south_bt2 = new JButton(new ImageIcon("image/cancel.gif"));
		south_bt3 = new JButton(new ImageIcon("image/help.gif"));
		south_bt1.addActionListener(this);
		south_bt2.addActionListener(this);
		south_bt3.addActionListener(this);
		south_Panel.add(south_bt1);
		south_Panel.add(south_bt2);
		south_Panel.add(south_bt3);

		this.add(north_Label, "North");
		this.add(jtp, "Center");
		this.add(south_Panel, "South");

		this.setIconImage(icon);
		this.setTitle("qq��½���棨߶߶������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(350, 240);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 150);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == center_bt) {
			System.out.println("�û������ �������");
		} 
		else if (e.getSource() == south_bt1) {
			
			System.out.println("�û�����˵�½");
			User u = new User();
			u.setUserId(this.user.getText().trim());
			u.setPasswd(new String(this.password.getPassword()));
			
			if (new QQClientUser().checkUser(u)) {
				
				FriendsListView list = new FriendsListView(u.getUserId());

				ManagerFriendList.addFriendsList(u.getUserId(), list);
				// ����������ͺ����Ƿ���������
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManegerClientConnServerThread
									.getClientConnServerThread(u.getUserId())
									.getSocket().getOutputStream());
					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "�û����������������");
			}
		} else if (e.getSource() == south_bt2) {
			System.out.println("�û������ ȡ��");
		} else if (e.getSource() == south_bt3) {
			System.out.println("�û������ ע����");
		}
	}
}
