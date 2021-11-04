package com.welcomeToJeju.moj.handler.theme.myTheme;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.theme.ThemeHandlerHelper;
import com.welcomeToJeju.util.Prompt;

public class MyThemeUpdateHandler implements Command {

  ThemeDao themeDao;
  SqlSession sqlSession;

  public MyThemeUpdateHandler(ThemeDao themeDao, SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[나의 테마 수정하기]");

    Theme theme = (Theme) request.getAttribute("theme");

    if (theme == null) {
      System.out.println("테마 없음!");
      return;
    }

    String title = Prompt.inputString("테마 제목 > ");

    // 카테고리
    Category category = new ThemeHandlerHelper(themeDao).promptCategory();

    // 해시태그
    List<String> hashtagList = new ArrayList<>();
    while (true) {
      String hashtag = Prompt.inputString("해시태그(완료: 엔터) > ");
      if (hashtag.equals("") || hashtag.length() == 0) {
        break;
      }
      hashtagList.add(hashtag);
    }

    // 공개
    int isPublic = 0;
    String publicOption = Prompt.inputString("공개(Y/n) > ");
    if (publicOption.equalsIgnoreCase("y") || publicOption.equals("")) {
      isPublic = 1;
    }

    String input = Prompt.inputString("수정하기(y/N) > ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("나의 테마 수정하기 취소!");
      return;
    }

    theme.setTitle(title);
    theme.setCategory(category);
    theme.setHashtags(hashtagList);
    theme.setIsPublic(isPublic);

    try {
      themeDao.deleteHashtag(theme.getNo());
      for (String hashTag : theme.getHashtags()) {
        themeDao.insertHashtag(theme.getNo(), hashTag);
      }
      themeDao.update(theme);
      sqlSession.commit();
      System.out.println("나의 테마 수정하기 성공!");

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println("나의 테마 수정하기 실패!");
    }
  }


}
