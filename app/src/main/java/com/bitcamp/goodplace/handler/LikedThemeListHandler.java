package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Theme;

public class LikedThemeListHandler implements Command{

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[좋아요 목록보기]");

    if (AuthLoginHandler.getLoginUser().getLikedThemes().size() == 0) {
      System.out.println("등록된 좋아요 없음!");
      System.out.println();
      return;
    }

    for (Theme theme : AuthLoginHandler.getLoginUser().getLikedThemes()) {
      System.out.printf("<%d>\n", theme.getNo());
      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
      System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
      System.out.println();
    }
  }


}