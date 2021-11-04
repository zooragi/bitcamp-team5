package com.welcomeToJeju.moj.handler.likedTheme;

import java.util.Collection;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class LikedThemeAddHandler implements Command {

  ThemeDao themeDao;
  PlaceDao placeDao;
  SqlSession sqlSession;

  public LikedThemeAddHandler(ThemeDao themeDao, PlaceDao placeDao, SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.placeDao = placeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 좋아요 누르기]");

    String title = Prompt.inputString("테마(취소 : 엔터) > ");

    if (title.equals("") || title.length() == 0) {
      System.out.println("테마 좋아요 누르기 취소!");
      System.out.println();
      return;
    }

    Theme theme = themeDao.findByTitle(title);

    if (theme == null) {
      System.out.println("테마 없음!");
      return;
    }

    if (theme.getOwner().getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("자기의 테마를 목록에 더할 수 없음!");
      return;
    }

    // 비공개 테마도 등록
    Collection<Place> placeList = placeDao.findByThemeNo(theme.getNo());
    int no = 1;
    for (Place place : placeList) {
      System.out.printf("<%d>\n", no++);
      System.out.printf("장소 이름 > %s\n", place.getStoreName());
      System.out.printf("장소 주소 > %s\n", place.getStoreAddress());
      System.out.printf("위도 > %s\n", place.getxCoord());
      System.out.printf("경도 > %s\n", place.getyCoord());
      System.out.printf("장소 후기> %s\n", place.getComments().toString());
      System.out.println();
    }

    while(true) {
      String input = Prompt.inputString("정말로 등록 하시겠습니까?(y/N) : ");
      if(input.equalsIgnoreCase("y")) {
        break;
      } else if (input.equalsIgnoreCase("n")) {
        System.out.println("등록 취소");
        return;
      } else {
        System.out.println("잘못된 입력입니다. 다시 입력하세요.");
        continue;
      }
    }

    try {
      themeDao.insertLikedTheme(theme.getNo(), AuthLoginHandler.getLoginUser().getNo());
    } catch (Exception e) {
      System.out.println("이미 좋아하는 테마!");
      return;
    }

    sqlSession.commit();
    System.out.println("테마 좋아요 누르기 성공!");
  }


}
