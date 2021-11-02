package com.welcomeToJeju.moj.handler.theme.myTheme;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.theme.ThemeHandlerHelper;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class MyThemeAddHandler implements Command {

  ThemeDao themeDao;
  SqlSession sqlSession;

  public MyThemeAddHandler(ThemeDao themeDao, SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[나의 테마 만들기]");

    Theme theme = new Theme();

    theme.setTitle(Prompt.inputString("테마 제목(취소 : 엔터) > "));

    if (theme.getTitle().equals("") || theme.getTitle().length() == 0) {
      System.out.println("나의 테마 만들기 취소!");
      return;
    }

    // 카테고리
    theme.setCategory(new ThemeHandlerHelper(themeDao).promptCategory());

    // 해시태그
    while (true) {
      String hashtag = Prompt.inputString("해시태그(완료 : 엔터) > ");
      if (hashtag.equals("") || hashtag.length() == 0) {
        break;
      }
      theme.getHashtags().add(hashtag);
    }
    
    String isPublic = Prompt.inputString("공개(Y/n) > ");
    if (isPublic.equalsIgnoreCase("y") || isPublic.length() == 0) {
      theme.setIsPublic(1);
    }

    theme.setOwner(AuthLoginHandler.getLoginUser());

    // 같은 해시 태그를 입력했을 때?
    try {
      themeDao.insert(theme);
      for (String hashtag : theme.getHashtags()) {
        themeDao.insertHashtag(theme.getNo(), hashtag);
      }
      sqlSession.commit();
      System.out.println("나의 테마 만들기 성공!");

    } catch (Exception e) {
      System.out.println(e);
      sqlSession.rollback();
      System.out.println("나의 테마 만들기 실패!");
    }
  }


}
