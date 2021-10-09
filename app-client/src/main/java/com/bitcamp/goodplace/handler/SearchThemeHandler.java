package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.util.Prompt;

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
      Theme theme = themeDao.search(input);
      PlaceHandlerHelper.printPlaceInfo(theme);
      
      return;
    }
  }
}