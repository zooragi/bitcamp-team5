package com.bitcamp.goodplace.Handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapHandler {
  User user;
  User user2;
  public MyMapHandler(User user) {
    this.user = user;
  }

  public void add() {
    Theme theme = new Theme();
    ArrayList<Theme> themeList = (ArrayList<Theme>) user.getThemeList();
    ArrayList<String> hashtagList = (ArrayList<String>) theme.getHashtags();
    System.out.println("나의 테마 등록");

    theme.setTitle(Prompt.inputString("테마 이름을 입력하세요> "));
    while (true) {
      String input = Prompt.inputString("해시 태그를 입력하세요(완료: 빈 문자열)> ");
      if (input.length() == 0)
        break;

      hashtagList.add(input);
    }
    if (Prompt.inputString("공개 설정 하시겠습니까?(y/N)> ").equalsIgnoreCase("y")) {
      theme.setPublic(true);
    }
    themeList.add(theme);
  }

  public void list() {
    int index = 1;
    System.out.println("[테마 목록 조회]");
    if (user.getThemeList().size() == 0) {
      System.out.println("등록된 테마가 없습니다.");
      return;
    }
    for (Theme list : user.getThemeList()) {
      System.out.printf("(%d)\n", index++);
      System.out.printf("테마 제목 : %s\n", list.getTitle());
      System.out.printf("해시 태그 : %s\n", list.getHashtags().toString());
      System.out.printf("공개 여부 : %s\n", list.isPublic() ? "공개" : "비공개");
      System.out.println();
    }
  }


  public void delete() {
    System.out.println("[테마 삭제]");
    String title = Prompt.inputString("테마 이름을 입력하세요> ");

    Theme theme = findByTitle(title);

    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)> ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 삭제를 취소하였습니다.");
      return;
    }

    user.getThemeList().remove(theme);

    System.out.println("테마를 삭제하였습니다.");
  }


  public void update() {
    System.out.println("[테마 변경]");
    String title = Prompt.inputString("수정할 테마 제목? ");

    Theme theme = findByTitle(title);

    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    String newTitle = Prompt.inputString(String.format("제목(%s)? ", theme.getTitle()));

    List<String> hashtagList = new ArrayList<>();

    while (true) {
      String input = Prompt.inputString("해시 태그(완료: 빈 문자열) : ");
      if (input.length() == 0)
        break;

      hashtagList.add(input);

    }

    if(Prompt.inputString("공개 설정 하시겠습니까?(y/N) : ").equalsIgnoreCase("y")) {
      theme.setPublic(true);
    } else {
      theme.setPublic(false);
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)> ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 변경을 취소하였습니다.");
      return;
    }

    theme.setTitle(newTitle);
    theme.setHashtags(hashtagList);

    System.out.println("테마를 변경하였습니다.");
  }

  private Theme findByTitle(String themeTitle) {
    ArrayList<Theme> themeList = (ArrayList<Theme>) user.getThemeList();
    for (Theme list : themeList) {
      if (list.getTitle().equals(themeTitle)) {
        return list;
      }
    }
    return null;
  }



}


