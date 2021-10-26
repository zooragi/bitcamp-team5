package com.welcomeToJeju.moj.handler;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class SearchUserHandler implements Command {
	UserDao userDao;
	ThemePrompt themePrompt;
	SqlSession sqlSession;
  public SearchUserHandler(UserDao userDao,ThemePrompt themePrompt,SqlSession sqlSession) {
  	this.userDao = userDao;
  	this.themePrompt = themePrompt;
  	this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[유저 검색 하기]");

    while (true) {
      String input = Prompt.inputString("유저 닉네임(취소 : 엔터) > ");
      User user = userDao.findByName(input);
      
      if(user == null) {
      	System.out.println("등록된 회원 아닙니다");
      	continue;
      }
      int currentViewCount = user.getViewCount();
      
  		HashMap<String,Object> params = new HashMap<>();
  		params.put("userNo", user.getNo());
  		params.put("viewCnt", currentViewCount+1);
      userDao.viewCountUpdate(params);
      
      System.out.printf("[%s]유저의 테마 목록\n", user.getNickname());
      themePrompt.printMyList(user);
      System.out.println();
      
      sqlSession.commit();
      
      return;
    }
  }
}