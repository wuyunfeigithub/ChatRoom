package com.qq.client.model;

import com.qq.common.*;

public class QQClientUser {
	public boolean checkUser(User u)
	{
		return new QQClientConnServer().sendLoginInfoToServer(u);
	}
}
