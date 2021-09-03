package com.bitcamp.goodplace.Handler;

import com.bitcamp.goodplace.domain.Theme;

public abstract class AbstractMyMapHandler implements Command{
	public Theme findByTitle(String themeTitle) {
		for(Theme theme : AuthLoginHandler.getLoginUser().getThemeList()) {
			if(theme.getTitle().equals(themeTitle)) return theme;
		}
		return null;
	}
}
