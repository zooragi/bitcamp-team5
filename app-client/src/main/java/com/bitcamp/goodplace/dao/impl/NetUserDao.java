package com.bitcamp.goodplace.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;

public class NetUserDao implements UserDao{
	
	RequestAgent requestAgent;
	
	public NetUserDao(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public void insert(User user) throws Exception {
		requestAgent.request("user.insert", user);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
	}

	@Override
	public User search(String name) throws Exception {
		requestAgent.request("user.search", name);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
		return requestAgent.getObject(User.class);
	}


	
}
