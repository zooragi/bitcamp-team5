package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class BookmarkDeleteHandler extends AbstractBookmarkHandler{

  public BookmarkDeleteHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {
    System.out.println("[북마크 삭제]");

    String themeTitle = Prompt.inputString("테마 이름을 입력하세요>");
    Theme user = findByTheme(themeTitle);

    if (user == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제 하시겠습니까?(y/N)>");
    if (input.equalsIgnoreCase("n")) {
      return;
    }
    System.out.println("테마를 삭제했습니다. ");
    AuthLoginHandler.getLoginUser().getBookMarks().remove(user);
  }

}
