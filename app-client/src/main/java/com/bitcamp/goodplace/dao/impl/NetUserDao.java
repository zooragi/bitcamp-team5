package com.bitcamp.goodplace.dao.impl;

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
	
}
