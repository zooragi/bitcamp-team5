package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class LikedThemeAddHandler extends AbstactLikedThemeHandler{

  public LikedThemeAddHandler(List<User> userList) {
    super(userList);
  }

  @Override
  public void execute(CommandRequest request) {
    while (true) {
      System.out.println("[좋아요 등록하기]");

      String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

      if (title.equals("") || title.length() == 0) {
        System.out.println("좋아요 등록하기 취소!");
        System.out.println();
        return;
      }

      Theme theme = findByTitle(title);

      if (theme == null) {
        System.out.println("등록된 테마 없음!");
        System.out.println();
        continue;
      }

      for (Theme list : AuthLoginHandler.getLoginUser().getLikedThemes()) {
        if (theme == list) {
          System.out.println("이미 등록한 좋아요!");
          return;
        }
      }

      if (theme.getThemeOwnerName().equals(AuthLoginHandler.getLoginUser().getNickName())) {
        System.out.println("본인의 테마 좋아요 등록 불가!");
        return;
      }

      AuthLoginHandler.getLoginUser().getLikedThemes().add(theme);
      System.out.println("좋아요 등록 완료!");
    }
  }


}