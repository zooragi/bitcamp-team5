package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class SearchHashtagHandler implements Command {

	ThemeDao themeDao;
	
  public SearchHashtagHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[해시 태그 검색하기]");

    while (true) {
      String input = Prompt.inputString("해시 태그(취소 : 엔터) > ");
      if(input.equals("")) {
        System.out.println("해시 태그 검색 취소!");
        return;
      }

      ArrayList<Theme> searchedThemeList = (ArrayList<Theme>) themeDao.hashtagSearch(input);
      
      if (searchedThemeList.size() == 0) {
        System.out.println("해당하는 테마 없음!");
        continue;
      }

      System.out.printf("[%s]의 검색결과\n", input);
      ThemeHandlerHelper.printList(searchedThemeList);
      
      return;
    }
  }
}