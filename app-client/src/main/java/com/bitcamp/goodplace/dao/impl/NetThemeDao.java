package com.bitcamp.goodplace.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.request.RequestAgent;

public class NetThemeDao implements ThemeDao{
	RequestAgent requestAgent;
	
	public NetThemeDao(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}
	
	public void insert(Theme theme) throws Exception {
		requestAgent.request("theme.insert", theme);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
	}
	
	public List<Theme> findAll() throws Exception {
		requestAgent.request("theme.list", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(Theme.class));
	}
}
