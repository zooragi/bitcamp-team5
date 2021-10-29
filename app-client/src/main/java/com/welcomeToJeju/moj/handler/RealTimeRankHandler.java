package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;

public class RealTimeRankHandler implements Command{
	
	ThemeDao themeDao;
	
  public RealTimeRankHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }

  public void execute(CommandRequest request) throws Exception {
    int i = 1;
    System.out.println("[실시간 테마 순위 보기]");
    for(Theme theme : themeDao.sortThemeByViewCount()) {
      System.out.printf("%d위. %s (조회수 : %d) > \n",i,theme.getTitle(),theme.getViewCount());
      i++;
    }
  }

}
