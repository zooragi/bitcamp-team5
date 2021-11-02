package com.welcomeToJeju.moj.handler.theme.myTheme;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class MyThemeDeleteHandler implements Command {

  ThemeDao themeDao;
  SqlSession sqlSession;

  public MyThemeDeleteHandler(ThemeDao themeDao, SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[나의 테마 삭제하기]");

    Theme theme = (Theme) request.getAttribute("theme");

    String input = Prompt.inputString("삭제하기(y/N) > ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("나의 테마 삭제하기 취소!");
      return;
    }

    try {
      themeDao.deleteAllLikedThemeByThemeNo(theme.getNo());
      themeDao.deleteHashtag(theme.getNo());
      themeDao.delete(theme.getNo());
      sqlSession.commit();
      System.out.println("나의 테마 삭제하기 성공!");

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println("나의 테마 삭제하기 실패!");
    }
  }


}
