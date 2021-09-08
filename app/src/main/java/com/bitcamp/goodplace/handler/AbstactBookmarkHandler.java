package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Theme;

public abstract class AbstactBookmarkHandler implements Command{
	
	protected Theme findByTheme(String themeTitle) {
		for (Theme theme : AuthLoginHandler.getLoginUser().getThemeList()) {
			if (theme.getTitle().equals(themeTitle)) {
				return theme;
			}
		}
		return null;
	}
}
