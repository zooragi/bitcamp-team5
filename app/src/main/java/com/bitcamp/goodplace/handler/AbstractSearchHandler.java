package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.domain.User;

public abstract class AbstractSearchHandler implements Command{
	List<User> userList;
	
	public AbstractSearchHandler(List<User> userList) {
		this.userList = userList;
	}
}
