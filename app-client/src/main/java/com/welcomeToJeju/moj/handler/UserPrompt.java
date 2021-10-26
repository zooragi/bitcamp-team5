package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public ArrayList<User> rank() throws Exception {
		ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
		ArrayList<User> ascThemeList = new ArrayList<>();
		for (User user : userList) {
			if(user.getNickname().equals("관리자")) continue;
				ascThemeList.add(user);
		}

		Collections.sort(ascThemeList);
		return ascThemeList;
	}

  public User findByNo(int userNo) throws Exception {
  	return userDao.findByNo(userNo);
  }
  
  public User findByName(String userName) throws Exception {
    return userDao.findByName(userName);
  }
  
	public void increaseReportedCount(int userNo, int reportedCnt) throws Exception {
    userDao.reportedCountUpdate(userNo, reportedCnt);
	}
	
	public void increaseWariningCount(int userNo, int warningCnt) throws Exception {
    userDao.warnedCountUpdate(userNo, warningCnt);
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
	
	public boolean isAlreadyRegisterLikedUser(User user,ArrayList<User> list) throws Exception {
		for(User u : list) {
			if(user.getNo() == u.getNo()) {
				return true;
			}
		}
		return false;
	}
	
	public String getByUserNo(int userNo) throws Exception {
		ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
		for(User u : userList) {
			if(u.getNo() == userNo) return u.getNickname();
		}
		return null;
	}
}
