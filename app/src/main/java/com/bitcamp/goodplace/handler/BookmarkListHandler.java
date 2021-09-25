package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Theme;

public class BookmarkListHandler implements Command{

  public void execute(CommandRequest request) {
    System.out.println("[북마크 목록]");

    int index = 1;
    for (Theme user : AuthLoginHandler.getLoginUser().getBookMarks()) {
      System.out.printf("(%d) ", index++);
      System.out.printf("%s ", user.getTitle());
      System.out.printf("%s", user.getHashtags().toString());
      System.out.println();
    }
  }
}
