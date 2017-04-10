package com.qq.client.tools;

import java.util.*;
import com.qq.client.view.*;

public class ManagerFriendList {

	private static HashMap<String, FriendsListView> hm = new HashMap<String, FriendsListView>();
	
	public static void addFriendsList(String ownerId, FriendsListView list){
		hm.put(ownerId, list);
	}
	
	public static FriendsListView getFriendsList(String ownerId){
		return (FriendsListView)hm.get(ownerId);
	}
}
