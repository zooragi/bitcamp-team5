package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.List;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class SearchHashtagHandler implements Command {

	ThemeDao themeDao;
	UserPrompt userPrompt;
	
  public SearchHashtagHandler(ThemeDao themeDao,UserPrompt userPrompt) {
  	this.themeDao = themeDao;
  	this.userPrompt = userPrompt;
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
      printList(searchedThemeList);
      
      return;
    }
  }
  
	private void printList(List<Theme> themeList) throws Exception {
		for (Theme theme : themeList) {
			System.out.printf("[%s] 테마명 > %s\n", userPrompt.getByUserNo(theme.getThemeOwnerNo()) , theme.getTitle());
		}
	}
}