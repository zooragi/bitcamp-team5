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

    ArrayList<Theme> bookmarkList = (ArrayList<Theme>)user.getBookMark();

    System.out.println("\n[북마크에 추가]");

    String themeTitle = Prompt.inputString("테마 이름> ");

    Theme theme = findByTheme(themeTitle);
    if(theme == null) {
      System.out.println("테마 없음");
      return;
    }
    bookmarkList.add(theme);
  }

  public void list() {
    System.out.println("[북마크 목록]");

    ArrayList<Theme> bookmarkList = (ArrayList<Theme>) user.getBookMark();
    for (Theme user : bookmarkList) {
      System.out.printf("\n - 목록 : %s\n",user.getTitle());
    }
  }

  public void delete() {
    System.out.println("[테마 삭제]");

    String themeTitle = Prompt.inputString("테마 이름> ");
    Theme user = findByTheme(themeTitle);

    if (user == null) {
      System.out.println("해당 테마가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제 하시겠습니까?(y/N)> ");
    if (input.equalsIgnoreCase("n")) {
      return;
    }
    this.user.getBookMark().remove(user);
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

  public void listPlace() {

    System.out.println("[장소 목록]");

    String themeTitle = Prompt.inputString("테마 이름> ");

    Theme theme = findByTheme(themeTitle);

    if(theme == null) {
      System.out.println("테마 없음");
      return;
    }

    int index = 1;

    for(Place list : theme.getPlaceList()) {
      System.out.printf("(%d)\n",index++);
      System.out.printf("가게명 : %s\n",list.getStoreName());
      System.out.printf("주소 : %s\n",list.getAddress());
      System.out.printf("테마 : %s\n",list.getThema());
      System.out.println();
    }
  }


}
