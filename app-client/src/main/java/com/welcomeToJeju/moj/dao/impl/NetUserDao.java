package com.welcomeToJeju.moj.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.request.RequestAgent;

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
  public void delete(int userNo) throws Exception {
    requestAgent.request("user.delete",Integer.toString(userNo));

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
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

	@Override
	public void update(User user) throws Exception {
		requestAgent.request("user.update", user);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
	}

	@Override
	public List<User> findAll() throws Exception {
		requestAgent.request("user.selectList", null);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
		return new ArrayList<>(requestAgent.getObjects(User.class));
	}
	
  @Override
  public void userLikedUserInsert(int likedUserNo, int loginUserNo) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("likedUserNo",Integer.toString(likedUserNo));
    params.put("loginUserNo",Integer.toString(loginUserNo));

    requestAgent.request("user.likedUser.insert", params);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("좋아하는 유저 등록 실패!");
    }
  }
  public void userLikedUserDelete(int likedUserNo, int loginUserNo) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("likedUserNo",Integer.toString(likedUserNo));
    params.put("loginUserNo",Integer.toString(loginUserNo));

    requestAgent.request("user.likedUser.delete", params);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("좋아하는 유저 등록 실패!");
    }
  }
  
  @Override
  public List<String> likedUserFindAll(int loginUserNo) throws Exception {
    requestAgent.request("user.likedUser.list",Integer.toString(loginUserNo));
    if(!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      return null;
    }
    return new ArrayList<>(requestAgent.getObjects(String.class));
  }
	
}
