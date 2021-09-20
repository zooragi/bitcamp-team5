package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.util.Prompt;

public class BookmarkAddHandler extends AbstactBookmarkHandler{

  public void execute(CommandRequest request) {
    int viewCount = 0;

    System.out.println("[북마크에 등록]");

    String themeTitle = Prompt.inputString("테마 이름을 입력하세요>");

    Theme theme = findByTheme(themeTitle);
    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다. ");
      return;
    }
    System.out.println("북마크를 추가했습니다.");
    viewCount = theme.getViewCount();
    theme.setViewCount(++viewCount);
    System.out.println(theme.getViewCount());
    AuthLoginHandler.getLoginUser().getBookMarks().add(theme);
  }

}
