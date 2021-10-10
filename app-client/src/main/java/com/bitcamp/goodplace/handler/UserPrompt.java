package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;

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
}
