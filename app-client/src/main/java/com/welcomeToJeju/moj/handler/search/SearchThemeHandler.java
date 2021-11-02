package com.welcomeToJeju.moj.handler.search;

import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.PlaceHandlerHelper;
import com.welcomeToJeju.util.Prompt;

public class SearchThemeHandler implements Command {

  ThemeDao themeDao;
  SqlSession sqlSession;

  public SearchThemeHandler(ThemeDao themeDao, SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 검색하기]");

    while (true) {
      String input = Prompt.inputString("테마(취소 : 엔터) > ");

      if(input.equals("") || input.length() == 0) {
        System.out.println("테마 검색하기 취소!");
        return;
      }

      Theme theme = themeDao.findByTitle(input);

      if(theme == null) {
        System.out.println("테마 없음!");
        continue;
      }

      // 조회수 +1
      int viewCount = theme.getViewCount();
      HashMap<String,Object> params = new HashMap<>();
      params.put("themeNo", theme.getNo());
      params.put("viewCnt", viewCount + 1);
      themeDao.updateViewCount(params);
      sqlSession.commit();

      // 안 됨
      System.out.printf("[%s] 검색 결과\n", input);
      PlaceHandlerHelper.printPlaceInfo(theme);

      return;
    }
  }


}
