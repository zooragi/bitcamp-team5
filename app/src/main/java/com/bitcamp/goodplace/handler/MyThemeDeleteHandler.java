package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyThemeDeleteHandler extends AbstractMyMapHandler{

  public MyThemeDeleteHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {
    System.out.println("[테마 삭제하기]");
    String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

    if(title.equals("")) {
      System.out.println("나의 테마 삭제 취소!");
      return;
    }

    Theme theme = findByTitle(title);

    if (theme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 삭제 취소!");
      return;
    }

    AuthLoginHandler.getLoginUser().getThemeList().remove(theme);

    System.out.println("테마 삭제 완료!");
  }

}
