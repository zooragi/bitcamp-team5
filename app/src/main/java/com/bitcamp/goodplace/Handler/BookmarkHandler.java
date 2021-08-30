package com.bitcamp.goodplace.Handler;

import java.util.ArrayList;
import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class BookmarkHandler {

  User user;

  public BookmarkHandler(User user) {
    this.user = user;

  }

  public void add() {

    ArrayList<Theme> bookmarkList = (ArrayList<Theme>)user.getBookMarks();

    System.out.println("[북마크에 등록]");

    String themeTitle = Prompt.inputString("테마 이름을 입력하세요>");

    Theme theme = findByTheme(themeTitle);
    if(theme == null) {
      System.out.println("해당 이름의 테마가 없습니다. ");
      return;
    }
    System.out.println("북마크를 추가했습니다.");
    bookmarkList.add(theme);
  }

  public void list() {
    System.out.println("[북마크 목록]");

    ArrayList<Theme> bookmarkList = (ArrayList<Theme>) user.getBookMarks();

    int index = 1;
    for (Theme user : bookmarkList) {
      System.out.printf("(%d) ", index++);
      System.out.printf("%s ",user.getTitle());
      System.out.printf("%s", user.getHashtags().toString());
      System.out.println();
    }
  }

  public void delete() {
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
    this.user.getBookMarks().remove(user);
  }

  private Theme findByTheme(String themeTitle) {

    ArrayList<Theme> themeList = (ArrayList<Theme>) user.getThemeList();
    for (Theme user : themeList) {
      if (user.getTitle().equals(themeTitle)) {
        return user;
      }
    }
    return null;
  }

}
