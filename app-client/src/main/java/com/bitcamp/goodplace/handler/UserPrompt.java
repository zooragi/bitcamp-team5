package com.bitcamp.goodplace.handler;

import java.util.ArrayList;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;

public class UserPrompt {

	RequestAgent requestAgent;
	
	public UserPrompt(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}
	
  public Theme findByTitle(String themeTitle) throws Exception {
  	requestAgent.request("user.selectList", null);
  	ArrayList<User> userList = new ArrayList<>(requestAgent.getObjects(User.class));
    for(User user : userList) {
      for(Theme theme : user.getThemeList())
        if(theme.getTitle().equals(themeTitle)) {
          return theme;
        }
    }
    return null;
  }
	
}
