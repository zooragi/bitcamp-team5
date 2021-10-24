package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class SearchThemeHandler implements Command {

	ThemeDao themeDao;
	
  public SearchThemeHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 검색하기]");

    while (true) {
      String input = Prompt.inputString("테마 이름(취소 : 엔터) > ");
      if(input.equals("")) {
        System.out.println("테마 검색 취소!");
        return;
      }
      Theme theme = themeDao.findByTitle(input);
      
      if(theme == null) {
      	System.out.println("검색된 테마가 없음");
      	continue;
      }
      
      int currentCount = theme.getViewCount();
      
      themeDao.viewCountUpdate(theme.getNo(), currentCount+1);
      PlaceHandlerHelper.printPlaceInfo(theme);
      
      return;
    }
  }
}