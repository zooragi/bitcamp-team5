package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public class ThemePrompt {

	ThemeDao themeDao;
	
	public ThemePrompt(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}
	
	public void printMyList(User user) throws Exception {
		ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findAll();
		for(Theme theme : themeList) {
			if(theme.getThemeOwnerName().equals(user.getNickName()) && theme.isPublic() == true) {
				System.out.printf("테마 > %s\n",theme.getTitle());
			}
		}
	}
	
	public ArrayList<Theme> rank() throws Exception {
		ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findAll();
		ArrayList<Theme> ascThemeList = new ArrayList<>();
		for (Theme theme : themeList) {
			if (theme.isPublic() == true)
				ascThemeList.add(theme);
		}

		Collections.sort(ascThemeList);
		return ascThemeList;
	}
	
	public Theme findByTitle(String themeTitle) throws Exception {
  	ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findAll();
      for(Theme theme : themeList)
        if(theme.getTitle().equals(themeTitle)) {
          return theme;
        }
    
    return null;
  }
	
	public void increaseReportedCount(Theme theme) throws Exception {
    int reportCount = theme.getReportedCount();
    theme.setReportedCount(reportCount+1);
    themeDao.update(theme);
	}
	
	public List<Theme> setCountedThemes() throws Exception {
		ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findAll();
		ArrayList<Theme> reportedThemes = new ArrayList<>();
		for(Theme theme : themeList) {
			if(theme.getReportedCount() != 0) {
				reportedThemes.add(theme);
			}
		}
		return reportedThemes;
	}
}
