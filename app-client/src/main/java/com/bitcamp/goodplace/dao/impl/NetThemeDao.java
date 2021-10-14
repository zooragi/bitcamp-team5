package com.bitcamp.goodplace.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.request.RequestAgent;

public class NetThemeDao implements ThemeDao{
	RequestAgent requestAgent;
	
	public NetThemeDao(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}
	
	@Override
	public void insert(Theme theme) throws Exception {
		requestAgent.request("theme.insert", theme);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
	}
	
	@Override
	public List<Theme> findAll() throws Exception {
		requestAgent.request("theme.list", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(Theme.class));
	}

	@Override
	public void update(Theme theme) throws Exception {
		requestAgent.request("theme.update", theme);		
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
	}

	@Override
	public String delete(String themeTitle) throws Exception {
		requestAgent.request("theme.delete", themeTitle);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
		return requestAgent.getObject(String.class);
	}

	@Override
	public void placeInsert(Place place) throws Exception {
		requestAgent.request("theme.place.insert", place);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
	}

	@Override
	public String placeDelete(Place place) throws Exception {
		requestAgent.request("theme.place.delete", place);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
		return requestAgent.getObject(String.class);
	}

	@Override
	public Theme search(String title) throws Exception {
		requestAgent.request("theme.search", title);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
		return requestAgent.getObject(Theme.class);
	}

	@Override
	public List<Theme> hashtagSearch(String hashtagName) throws Exception {
		requestAgent.request("theme.hashtag.search", hashtagName);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
		
		return new ArrayList<>(requestAgent.getObjects(Theme.class));
	}
	
	@Override
	public void likedThemeInsert(String themeTitle, String userName) throws Exception{
		HashMap<String, String> params = new HashMap<>();
	  params.put("themeTitle",themeTitle);
	  params.put("userName",userName);
	  requestAgent.request("theme.liked.insert", params);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
	}
	
	@Override
	public void likedThemeDelete(String themeTitle,String userName) throws Exception{
		HashMap<String, String> params = new HashMap<>();
	  params.put("themeTitle",themeTitle);
	  params.put("userName",userName);
	  requestAgent.request("theme.liked.delete", params);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
	}
	
	@Override
	public List<Theme> likedThemeList(String userName) throws Exception{
		requestAgent.request("theme.liked.list", userName);
		
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(Theme.class));
	}
  
}
