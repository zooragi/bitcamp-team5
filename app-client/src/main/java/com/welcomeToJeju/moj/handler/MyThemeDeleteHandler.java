package com.welcomeToJeju.moj.handler;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.request.RequestAgent;
import com.welcomeToJeju.util.Prompt;

public class MyThemeDeleteHandler implements Command {

	ThemeDao themeDao;
	SqlSession sqlSession;
	
  public MyThemeDeleteHandler(ThemeDao themeDao,SqlSession sqlSession) {
  	this.themeDao = themeDao;
  	this.sqlSession = sqlSession;
  }

	public void execute(CommandRequest request) throws Exception{
		System.out.println("[테마 삭제하기]");

		String input = Prompt.inputString("삭제하기(y/N) > ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("테마 삭제 취소!");
			return;
		}
		
		Theme theme = (Theme) request.getAttribute("theme");

		
		themeDao.hashtagDelete(theme.getNo());
		themeDao.likedThemeAllDelete(theme.getNo());
		themeDao.delete(theme.getNo());
		sqlSession.commit();
		
		System.out.println("테마 삭제 완료!");
		System.out.println();
	}

}
