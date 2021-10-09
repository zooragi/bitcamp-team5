package com.bitcamp.goodplace.handler;

import java.util.ArrayList;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public class ThemePrompt {

	ThemeDao themeDao;
	
	public ThemePrompt(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}
	
	public void printList(User user) throws Exception {
		ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findAll();
		for(Theme theme : themeList) {
			if(theme.getThemeOwnerName().equals(user.getNickName())) {
				System.out.printf("테마 > %s",theme.getTitle());
			}
		}
	}
}
