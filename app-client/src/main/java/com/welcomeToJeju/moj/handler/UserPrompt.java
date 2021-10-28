package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.request.RequestAgent;

public class UserPrompt {

	UserDao userDao;
	
  public UserPrompt(UserDao userDao) {
  	this.userDao = userDao;
  }

  public User findByNo(int userNo) throws Exception {
  	return userDao.findByNo(userNo);
  }
  
  public User findByName(String userName) throws Exception {
    return userDao.findByName(userName);
  }
  
	public void increaseReportedCount(int userNo, int reportedCnt) throws Exception {
		HashMap<String,Object> params = new HashMap<>();
		params.put("userNo", userNo);
		params.put("reportedCnt", reportedCnt);
    userDao.updateReportedCount(params);
	}
	
	public void increaseWariningCount(int userNo, int warningCnt) throws Exception {
		HashMap<String,Object> params = new HashMap<>();
		params.put("userNo", userNo);
		params.put("warnedCnt", warningCnt);
    userDao.updateWarnedCount(params);
	}
	
	public List<User> setCountedUser() throws Exception {
		ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
		ArrayList<User> reportedThemes = new ArrayList<>();
		for(User user : userList) {
			if(user.getReportedCount() != 0) {
				reportedThemes.add(user);
			}
		}
		return reportedThemes;
	}
	
	public String getByUserNo(int userNo) throws Exception {
		ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
		for(User u : userList) {
			if(u.getNo() == userNo) return u.getNickname();
		}
		return null;
	}
}
