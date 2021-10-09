package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class SearchUserHandler implements Command {
	UserDao userDao;
	ThemePrompt themePrompt;
	
  public SearchUserHandler(UserDao userDao,ThemePrompt themePrompt) {
  	this.userDao = userDao;
  	this.themePrompt = themePrompt;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[유저 검색 하기]");

    while (true) {
      String input = Prompt.inputString("유저 닉네임(취소 : 엔터) > ");
      User user = userDao.search(input);
      
      if(user == null) {
      	System.out.println("등록된 회원 아닙니다");
      	continue;
      }
      System.out.printf("[%s]유저의 테마 목록\n", user.getNickName());
      themePrompt.printMyList(user);
      System.out.println();
      
      return;
    }
  }
}