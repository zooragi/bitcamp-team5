package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class LikedThemeDeleteHandler extends AbstactLikedThemeHandler {

  public LikedThemeDeleteHandler(List<User> userList) {
    super(userList);
  }

  @Override
  public void execute(CommandRequest request) {
    while (true) {
      System.out.println("[좋아요 삭제하기]");

      String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

      if(title.equals("") || title.length() == 0) {
        System.out.println("좋아요 삭제하기 취소!");
        System.out.println();
        return;
      }

      Theme theme = findByTitle(title);

      if (theme == null) {
        System.out.println("등록된 테마 없음!");
        System.out.println();
        continue;
      }

      String input = Prompt.inputString("삭제하기(y/N) > ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("좋아요 삭제하기 취소!");
        System.out.println();
        return;
      }

      AuthLoginHandler.getLoginUser().getLikedThemes().remove(theme);
      System.out.println("좋아요 삭제하기 완료!");
    }
  }


}