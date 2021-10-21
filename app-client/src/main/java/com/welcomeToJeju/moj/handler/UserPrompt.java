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
			if(user.getNickName().equals("관리자")) continue;
				ascThemeList.add(user);
		}

		Collections.sort(ascThemeList);
		return ascThemeList;
	}

  public User findByNo(int userNo) throws Exception {
  	ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
    for (User user : userList) {
      if (user.getNo() == userNo) {
        return user;
      }
    }
    return null;
  }
  
  public User findByName(String userName) throws Exception {
  	ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
    for (User user : userList) {
      if (user.getNickName().equals(userName)) {
        return user;
      }
    }
    return null;
  }
  
	public void increaseReportedCount(User user) throws Exception {
    int reportCount = user.getReportedCount();
    user.setReportedCount(reportCount+1);
    userDao.update(user);
	}
	public void increaseWariningCount(User user) throws Exception {
    int count = user.getWarningCount();
    user.setWarningCount(++count);
    userDao.update(user);
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
			if(u.getNo() == userNo) return u.getNickName();
		}
		return null;
	}
}
