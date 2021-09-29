package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public abstract class AbstractMyMapHandler implements Command {
	List<User> userList;

	public AbstractMyMapHandler(List<User> userList) {
		this.userList = userList;
	}

	public Theme findByTitle(String themeTitle) {
		for (Theme theme : AuthLoginHandler.getLoginUser().getThemeList()) {
			if (theme.getTitle().equals(themeTitle))
				return theme;
		}
		return null;
	}
}
