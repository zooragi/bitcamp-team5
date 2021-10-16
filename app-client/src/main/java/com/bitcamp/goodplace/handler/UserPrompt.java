package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;

public class UserPrompt {

	UserDao userDao;
	
  public UserPrompt(UserDao userDao) {
  	this.userDao = userDao;
  }
	
	public ArrayList<User> rank() throws Exception {
		ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
		ArrayList<User> ascThemeList = new ArrayList<>();
		for (User user : userList) {
				ascThemeList.add(user);
		}

		Collections.sort(ascThemeList);
		return ascThemeList;
	}

  public User findByName(String userNickName) throws Exception {
  	ArrayList<User> userList = (ArrayList<User>) userDao.findAll();
    for (User user : userList) {
      if (user.getNickName().equals(userNickName)) {
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
	
	public boolean isAlreadyRegisterLikedUser(User user,int userNo) throws Exception {
		for(int no: user.getLikedUserNo()) {
			if(no == userNo) {
				return true;
			}
		}
		return false;
	}
}
