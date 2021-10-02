package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class SearchThemeHandler extends AbstractSearchHandler {

  List<Theme> themeList;

  public SearchThemeHandler(List<User> userList, List<Theme> themeList) {
    super(userList);
    this.themeList = themeList;
  } 

  public void execute(CommandRequest request) {

    System.out.println("[테마 검색하기]");

    while (true) {
      String input = Prompt.inputString("테마 이름(취소 : 엔터) > ");
      if(input.equals("")) {
        System.out.println("테마 검색 취소!");
        return;
      }
      for (Theme theme : themeList) {
        if (theme.getTitle().contains(input) && theme.isPublic()) {
          System.out.printf("테마 제목 > %s\n", theme.getTitle());
          System.out.printf("[%s 님의 테마]\n", theme.getUserNickName().getUserNickName());
          System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
          System.out.printf("장소 > %s\n", theme.getPlaceList().toString());
          System.out.printf("조회수 > %d\n", theme.getViewCount() + 1);						
          theme.setViewCount(theme.getViewCount()+1);
          System.out.println();
          return;
        }
      }
      System.out.println("등록된 테마 없음!");
    }
  }
}
